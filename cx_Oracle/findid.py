import sys
import cx_Oracle

conn = ''

def findintable(tablename, id):
    try:
        global conn
        cur = conn.cursor()
        cur.execute('select * from ' + tablename)
        rows = cur.fetchall()
        for row in rows:
            if id in row:
                print tablename
                break
    except:
        return


def main():
    global conn
    conn = cx_Oracle.connect('main/main@163.183.178.163:1521/psdb06a')
    #conn = cx_Oracle.connect('dd/dd@163.183.178.163:1521/psdb06a')

    tables=[]
    cur = conn.cursor()
    cur.execute('select TABLE_NAME from all_tables')
    rows = cur.fetchall()

    for row in rows:
        tables.append(row[0])

    for table in tables:
        findintable(table, int(sys.argv[1]))

    for table in tables:
        findintable(table, sys.argv[1])







main()