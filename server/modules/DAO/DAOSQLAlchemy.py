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
		

	def addProduct(self, product: Product) -> Product:
		mapper = MapperSQLAlchemy()
		mappedProduct = mapper.mapProductToAlchemy(product)
		with Session(self.engine) as session:
			session.add(mappedProduct)
			result = session.scalar(sa.select(orm.Product).where(orm.Product.id == mappedProduct.id))
			session.commit()
			mappedFromAlchemy = mapper.mapProductFromSQLAlchemy(result)
			return mappedFromAlchemy
		#return productId

	def deleteProduct(self, product: Product) -> int:
		mapper = MapperSQLAlchemy()
		mappedProduct = mapper.mapProductToAlchemy(product)
		with Session(self.engine) as session:
			session.scalar(sa.delete(orm.Product).where(orm.Product.id == mappedProduct.id).returning(orm.Product))
			session.commit()
			return 1
		#return кол-во удаленных кортежей 

	def deleteProductById(self, product_id: uuid.UUID) -> int:
		mapper = MapperSQLAlchemy()
		with Session(self.engine) as session:
			session.scalar(sa.delete(orm.Product).where(orm.Product.id == product_id).returning(orm.Product))
			session.commit()
			return 1

	def updateProduct(self, product: Product) -> int:
		mapper = MapperSQLAlchemy()
		mappedProduct = mapper.mapProductToAlchemy(product)
		with Session(self.engine) as session:
			result = session.scalar(sa.update(orm.Product).where(orm.Product.id == mappedProduct.id).values(product_name = mappedProduct.product_name).returning(orm.Product))  
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

	def getProductById(self, product_id: uuid.UUID) -> Product:
		mapper = MapperSQLAlchemy()
		with Session(self.engine) as session:
			result = session.scalar(sa.select(orm.Product).where(orm.Product.id == product_id))
			session.commit()
			mappedFromAlchemy = mapper.mapProductFromSQLAlchemy(result)
			return mappedFromAlchemy
		

	def checkProductExistByName(self, product: Product) -> uuid.UUID:
		#return productId
		pass


class DAOSale(DAOSale): # todo доделать реализации методов с алчеми

	def __init__(self, engine):
		self.engine = engine

	def addSale(self, sale: Sale) -> Sale:
		mapper = MapperSQLAlchemy()
		mappedSale = mapper.mapSaleToAlchemy(sale)
		with Session(self.engine) as session:
			session.add(mappedSale)
			result = session.scalar(sa.select(orm.Sale).where(orm.Sale.id == mappedSale.id))
			session.commit()
			mappedFromAlchemy = mapper.mapSaleFromSQLAlchemy(result)
			return mappedFromAlchemy
		

	def deleteSale(self, sale: Sale) -> int:
		mapper = MapperSQLAlchemy()
		mappedSale = mapper.mapSaleToAlchemy(sale)
		with Session(self.engine) as session:
			session.scalar(sa.delete(orm.Sale).where(orm.Sale.id == mappedSale.id).returning(orm.Sale))
			session.commit()
			return 1

	def updateSale(self, sale: Sale) -> int:
		mapper = MapperSQLAlchemy()
		mappedSale = mapper.mapSaleToAlchemy(sale)
		with Session(self.engine) as session:
			session.scalar(sa.update(orm.Sale).where(orm.Sale.id == mappedSale.id).values(product_id = mappedSale.product_id,
																							quantity = mappedSale.quantity,
																							total_value = mappedSale.total_value,
																							date = mappedSale.date).returning(orm.Sale))
			session.commit()
			return 1

	def getAllSale(self, ) -> list:
		mapper = MapperSQLAlchemy()
		with Session(self.engine) as session:
			result = session.scalars(sa.select(orm.Sale)).all()
			session.commit()
			listOfSale = list()
			for i in result:
				mappedSale = mapper.mapSaleFromSQLAlchemy(i)
				listOfSale.append(mappedSale)
			return listOfSale

	def getAllSaleByProductId(self, product_id: uuid.UUID) -> list:
		mapper = MapperSQLAlchemy()
		with Session(self.engine) as session:
			result = session.scalars(sa.select(orm.Sale).where(orm.Sale.product_id == product_id)).all()
			session.commit()
			listOfSale = list()
			for i in result:
				mappedSale = mapper.mapSaleFromSQLAlchemy(i)
				listOfSale.append(mappedSale)
			return listOfSale
		
	def getSaleBySaleId(self, sale_id: uuid.UUID) -> Sale:
		mapper = MapperSQLAlchemy()
		with Session(self.engine) as session:
			result = session.scalar(sa.select(orm.Sale).where(orm.Sale.id == sale_id))
			session.commit()
			mappedSale = mapper.mapSaleFromSQLAlchemy(result)
			return mappedSale


	def checkSaleExistByProductNameAndDate(self, productName: str, saleDate: uuid.UUID) -> uuid.UUID:
		#return saleId
		pass



