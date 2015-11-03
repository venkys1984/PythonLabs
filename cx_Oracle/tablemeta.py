import sys
import cx_Oracle
import re

def main():
    conn = cx_Oracle.connect("main/main@163.183.178.163:1521/psdb06a")
    print conn.version
    print 'Getting data for table: ' + sys.argv[1]
    cur = conn.cursor()

    query = "select dbms_metadata.get_ddl( 'TABLE', upper('seismic_dataset_')) from dual"
    cur.execute(query)
    desc = []
    for result in cur:
        desc = result[0].read()

    newdesc = desc.split('\n')

    for line in newdesc:
        if re.search("PRIMARY KEY \(", line) and re.search("CONSTRAINT", line):
            words = line.split()
            key = words[-1]
            pkey = key[2:-2]
            print 'Primary Key: ' + pkey

    count = -1
    for line in newdesc:
        count = count + 1
        if re.search("FOREIGN KEY \(", line) and re.search("CONSTRAINT", line):
            words = line.split()
            nextline = newdesc[count+1]
            print 'Foreign Key: ' + words[-1][1:-1] + " " + nextline




    cur.close()
    conn.close()

main()
