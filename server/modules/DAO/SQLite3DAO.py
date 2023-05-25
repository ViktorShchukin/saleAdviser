#
from server.modules.DAO.DAOinterface import DAOProduct, DAOSale
from server.modules.DAO.ORMmodel import Product, Sale
import uuid

class DAOProductSQLite3(DAOProduct): # todo доделать реализации методов с алчеми

	def __init__(self, engine): #todo посмотреть как называется класса engine для аннотации типа 
		self.engine = engine
		

	def getProductById(self, productId: str) -> Product:
		with Session(self.engine) as session:
			#product = session.select #todo сделать селект продукта из бд 
			#session.commit()
			return Product


class DAOSaleSQLite3(DAOSale): # todo доделать реализации методов с алчеми

	def __init__(self, engine):
		self.engine = engine

	def getSaleByProductNameAndSaleId(self, productName: str, saleId: uuid.UUID) -> Sale:
		with Session(self.engine) as session:
			#sale = session.select #todo сделать селект продукта из бд 
			#session.commit()
			return Sale
		