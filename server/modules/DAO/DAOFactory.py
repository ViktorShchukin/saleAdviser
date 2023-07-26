# 
import sqlalchemy as sa

import server.modules.DAO.DAOInterface as interface
from server.modules.DAO.DAOSQLAlchemy import DAOProduct, DAOSale
from server.config.Config import Config

#generaltodo изменить все под стандарты пип8 пока еще не очень много написано 

class DAOFactory:
	"""todo сделать документацию к классу и методам"""
	def __init__(self, config: Config):
		self.config = config 
		
	def initConection(self,):
		self.engine = sa.create_engine(self.config.getParam("connectionString"))
		

	def getDAOProduct(self,) -> interface.DAOProduct:
		self.initConection()
		if self.config.getParam("databaseName") == "SQLite3":
			return DAOProduct(self.engine)
		else:
			raise RuntimeError("неизвастная база данных ") 

	def getDAOSale(self,) -> interface.DAOSale:
		self.initConection()
		if self.config.getParam("databaseName") == "SQLite3":
			return DAOSale(self.engine)
		else:
			raise RuntimeError("неизвастная база данных ")		

