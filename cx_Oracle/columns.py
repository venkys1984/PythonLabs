import sys
import cx_Oracle

def main():
    #print sys.argv[1]
    conn = cx_Oracle.connect('main/main@163.183.178.163:1521/psdb06a')
    cur = conn.cursor()
    cur.execute('select * from ' + sys.argv[1])
    # print cur.description

    for i in range(0,len(cur.description)):
        print cur.description[i][0]

    data = cur.fetchall()
    print data

main()