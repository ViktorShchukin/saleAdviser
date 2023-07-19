#
import uuid

import sqlalchemy as sa
from sqlalchemy.orm import Session

from server.modules.adviserModel.product import Product
from server.modules.adviserModel.sale import Sale
from server.modules.DAO.DAOInterface import DAOProduct, DAOSale
import server.modules.DAO.ormmodel.ORMmodel as orm
from server.modules.DAO.mapper.mapperSQLAlchemy import MapperSQLAlchemy


class DAOProduct(DAOProduct): # todo доделать реализации методов с алчеми

	def __init__(self, engine: sa.Engine): 
		self.engine = engine
		

	def addProduct(self, product: Product) -> uuid.UUID:
		mapper = MapperSQLAlchemy()
		mappedProduct = mapper.mapProductToAlchemy(product)
		with Session(self.engine) as session:
			session.add(mappedProduct)
			result = session.scalar(sa.select(orm.Product).where(orm.Product.id == mappedProduct.id))
			session.commit()
			return result.id
		#return productId

	def deleteProductById(self, product: Product) -> int:
		mapper = MapperSQLAlchemy()
		mappedProduct = mapper.mapProductToAlchemy(product)
		with Session(self.engine) as session:
			session.scalar(sa.delete(orm.Product).where(orm.Product.id == mappedProduct.id).returning(orm.Product))
			session.commit()
			return 1
		#return кол-во удаленных кортежей 

	def updateProductById(self, product: Product) -> int:
		mapper = MapperSQLAlchemy()
		mappedProduct = mapper.mapProductToAlchemy(product)
		with Session(self.engine) as session:
			result = session.scalar(sa.update(orm.Product).where(orm.Product.id == mappedProduct.id).values(product_name = orm.Product.product_name).returning(orm.Product))  
			session.commit()
			return 1

	def getAllProduct(self, ) -> list:
		mapper = MapperSQLAlchemy()
		with Session(self.engine) as session:
			result = session.scalars(sa.select(orm.Product)).all()
			listOfProduct = list()
			for i in result:
				mappedProduct = mapper.mapProductFromSQLAlchemy(i)
				listOfProduct.append(mappedProduct)
			return listOfProduct

	def getProductById(self, product: Product) -> Product:
		mapper = MapperSQLAlchemy()
		mappedProduct = mapper.mapProductToAlchemy(product)
		with Session(self.engine) as session:
			result = session.scalar(sa.select(orm.Product).where(orm.Product.id == mappedProduct.id))
			session.commit()
			mappedFromAlchemy = mapper.mapProductFromSQLAlchemy(result)
			return mappedFromAlchemy
		

	def checkProductExistByName(self, product: Product) -> uuid.UUID:
		#return productId
		pass


class DAOSale(DAOSale): # todo доделать реализации методов с алчеми

	def __init__(self, engine):
		self.engine = engine

	def addSaleByProductName(self, productName: str) -> uuid.UUID:
		#return saleId
		pass

	def deleteSaleByProductNameAndSaleId(self, productName: str, saleId: uuid.UUID) -> int:
		#return кол-во удаленных кортежей 
		pass

	def updateSaleByProductNameAndSaleId(self, productName: str, saleId: uuid.UUID) -> int:
		#return 
		pass

	def getAllSale(self, productName: str) -> list:
		#return List(Sale)
		pass

	def getSaleByProductNameAndSaleId(self, productName: str, saleId: uuid.UUID) -> Sale:
		with Session(self.engine) as session:
			#sale = session.select #todo сделать селект продукта из бд 
			#session.commit()
			return Sale
		

	def checkSaleExistByProductNameAndDate(self, productName: str, saleDate: uuid.UUID) -> uuid.UUID:
		#return saleId
		pass



