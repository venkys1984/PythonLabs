import sys
import cx_Oracle

users = {'dd':'dd','main':'main','dd2':'dd2','main2':'main2','sis_catalog':'sis_catalog'};
#users = {'dd2':'dd2','sis_catalog':'sis_catalog','sde':'sde'};
#users = {'SDS_INFO':'sds_info'};



conn = ''
curruser = ''

def findintable(tablename, sub):
    try:
        global conn
        global curruser
        cur = conn.cursor()
        cur.execute('select * from ' + tablename)

        rows = cur.fetchall()

        # for item in cur.description:
        #     print item[0]
        for row in rows:
            for idx, item in enumerate(row):
              if isinstance(item, basestring):
                if sub.lower() in item.lower():
                #if sub.lower() ==  item.lower():
                  print curruser, tablename, cur.description[idx][0], item
    except:
        return



def main():
    global conn
    global curruser
    for key in users.keys():
        curruser = key
        conn = cx_Oracle.connect(key + '/' + users[key] + '@163.183.178.163:1521/psdb06a')
        tables=[]
        cur = conn.cursor()
        cur.execute('select TABLE_NAME from all_tables')
        rows = cur.fetchall()

        for row in rows:
          tables.append(row[0])

        for table in tables:
          findintable(table, sys.argv[1])












main()
