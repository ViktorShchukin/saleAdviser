import modules.DAO.realizationDAO as realizationDAO
import modules.convertRowsFromDb.convert as convert 

if __name__ == "__main__":

	x = 'dataFile1.csv' #input('write name of file:')
	dao = realizationDAO.DAO("moms1.db")
	dao.createTable()
	convert.saveFileToDb(dao, x)
	dao.commit()












