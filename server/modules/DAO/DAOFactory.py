# 
import sqlalchemy as sa

import server.modules.DAO.DAOInterface as interface
# from server.modules.DAO.DAOSQLAlchemy import DAOProduct, DAOSale
from server.modules.DAO.DAOSQLite3 import DAOProduct, DAOSale
from server.config.Config import Config

# general todo изменить все под стандарты пип8 пока еще не очень много написано


class DAOFactory:
	"""todo сделать документацию к классу и методам"""
	def __init__(self, config: Config):
		self.config = config 
		
	def initConnection(self,):
		engine = sa.create_engine(self.config.getParam("connectionString"))
		return engine

	def getDAOProduct(self,) -> interface.DAOProductBase:
		# engine = self.initConnection()
		# todo create a normal interface for selecting DAO object
		# engine is used to init the SQLAlchemy DAO
		# now there is temporary solution, but it's need to be normal
		if self.config.getParam("databaseName") == "SQLite3":
			return DAOProduct(self.config.getParam("path"))
		else:
			raise RuntimeError("неизвастная база данных ") 

	def getDAOSale(self,) -> interface.DaoSaleBase:
		# engine = self.initConnection()
		# todo create a normal interface for selecting DAO object comments about it is above in getDAOProduct
		if self.config.getParam("databaseName") == "SQLite3":
			return DAOSale(self.config.getParam("path"))
		else:
			raise RuntimeError("неизвастная база данных ")		


if __name__ == '__main__':
	import uuid
	conf = Config()
	factory = DAOFactory(conf)
	# daoSale = factory.getDAOSale()
	# print(type(daoSale))
	# print(daoSale.getAllSale())
	# print(daoSale.getAllSaleByProductId(uuid.UUID("d6b9e0b6-807d-452b-80b0-c89c5cd75aaf")))
	daoProduct = factory.getDAOProduct()
	res = daoProduct.getAllProduct()
	print(res, "===========")
	res2 = daoProduct.getProductById(uuid.UUID("d6b9e0b6-807d-452b-80b0-c89c5cd75aaf"))
	print(res2, "++++")
	res3 = daoProduct.getProductById("d6b9e0b6-807d-452b-80b0-c89c5cd75aaf")
	print(res3, "-----")