# 
import sqlalchemy as sa

from server.modules.DAO.DAOinterface import DAOProduct, DAOSale
from server.modules.DAO.SQLite3DAO import DAOProductSQLite3 
from server.config.Config import Config

#generaltodo изменить все под стандарты пип8 пока еще не очень много написано 

class DAOFactory:
	"""todo сделать документацию к классу и методам"""
	def __init__(self, config: Config):
		self.config = config 
		
	def initConection(self):
		self.engine = sa.create_engine(self.config.getParam("connectionString"))
		

	def getDAOProduct(self) -> DAOProduct:
		if self.config.getParam("databaseName") == "SQLite3":
			return DAOProductSQLite3(self.engine)
		else:
			raise RuntimeError("неизвастная база данных ") 

	def getDAOSale(self) -> DAOSale:
		return DAOProductSQLite3(self.engine)		


	 