#
import modules.DAO.realizationDAO as realizationDAO
from datetime import datetime
import uuid


def convertLine(line):
	columns = line.split(";")
	name=columns[0].strip()
	quantity=columns[1].replace(',','.')
	total=columns[2].replace(' ','')
	#print('====',columns[3])
	date = datetime.strptime(columns[3].strip(), '%d.%m.%Y')
	return (str(uuid.uuid4()), name, quantity, total, date)


def saveFileToDb(dao, fileName):
	file = open(fileName,"r")
	line = file.readline()
	while len(line)!=0 :
		columns=convertLine(line)
		productId = dao.getProductIdOrCreate(columns[1])
		if columns[2] != '' and columns[1] != '':
			dao.saveToDb(columns[0], productId, columns[2], columns[3], columns[4] )
			#dao.commit()
		line=file.readline()
	


#dataFile1.csv


if __name__ == "__main__":
	dao = realizationDAO.DAO("moms1.db")
	saveFileToDb(dao,'dataFile1.csv')
	dao.commit()