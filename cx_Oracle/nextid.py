import sys
import cx_Oracle


def main():
    #conn = cx_Oracle.connect('main/main@163.183.178.163:1521/psdb06a')
    conn = cx_Oracle.connect('large/large@163.183.178.163:1521/psdb06a')
    print conn.version


    try:
        cur = conn.cursor()
        cur.execute('select SDS_Id_Seq.NextVal  from dual')
        rows = cur.fetchall()

        for row in rows:
            print row
    except:
        pass

    conn.close()




main()