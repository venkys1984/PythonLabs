import sys
import cx_Oracle


def main():
    #conn = cx_Oracle.connect('main/main@163.183.178.163:1521/psdb06a')
    conn = cx_Oracle.connect('large/large@163.183.178.163:1521/psdb06a')
    # print conn.version

    tables=[]
    cur = conn.cursor()
    cur.execute('select TABLE_NAME from all_tables')
    rows = cur.fetchall()

    for row in rows:
        tables.append(row[0])
    #     print row[0]

    for table in tables:
        q = "select count(*) from " + table
        try:
            cur.execute(q)
            count = cur.fetchall()[0][0]
            print table, count
        except:
            pass

    conn.close()




main()
