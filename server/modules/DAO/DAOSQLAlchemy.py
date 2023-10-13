#
import uuid

import sqlalchemy as sa
from sqlalchemy.orm import Session

from server.modules.adviserModel.product import Product
from server.modules.adviserModel.sale import Sale
import server.modules.DAO.DAOInterface as Base
import server.modules.DAO.ormmodel.ORMmodel as orm
from server.modules.DAO.mapper.mapperSQLAlchemy import MapperSQLAlchemy


class DAOProduct(Base.DAOProductBase): # todo доделать реализации методов с алчеми

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
			
			mappedFromAlchemy = mapper.mapProductFromSQLAlchemy(result)
			session.commit()
			return mappedFromAlchemy

	def getProductByName(self, product_name: str) -> Product:
		mapper = MapperSQLAlchemy()
		with Session(self.engine) as session:
			result = session.scalar(sa.select(orm.Product).where(orm.Product.product_name == product_name))
			
			mappedFromAlchemy = mapper.mapProductFromSQLAlchemy(result)
			session.commit()
			return mappedFromAlchemy

	def getProductByNameLike(self, searchProduct: str) -> list:
		mapper = MapperSQLAlchemy()
		with Session(self.engine) as session:
			newProductName = "%" + searchProduct + "%"
			result = session.query(orm.Product).filter(orm.Product.product_name.like(newProductName)).all()
			listOfProduct = list()
			for i in result:
				mappedProduct = mapper.mapProductFromSQLAlchemy(i)
				listOfProduct.append(mappedProduct)
			session.commit()
			return listOfProduct
		

	def checkProductExistByName(self, product: Product) -> uuid.UUID:
		#return productId
		pass


class DAOSale(Base.DaoSaleBase): # todo доделать реализации методов с алчеми

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

	def deleteSaleById(self, sale_id: uuid.UUID) -> int:
		mapper = MapperSQLAlchemy()
		with Session(self.engine) as session:
			session.scalar(sa.delete(orm.Sale).where(orm.Sale.id == sale_id).returning(orm.Sale))
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
			
			listOfSale = list()
			for i in result:
				mappedSale = mapper.mapSaleFromSQLAlchemy(i)
				listOfSale.append(mappedSale)
			session.commit()
			return listOfSale

	def getAllSaleByProductId(self, product_id: uuid.UUID) -> list:
		mapper = MapperSQLAlchemy()
		with Session(self.engine) as session:
			#print(product_id)
			#print(type(product_id))
			#print(type(orm.Sale.product_id))
			

			result = session.scalars(sa.select(orm.Sale).where(orm.Sale.product_id == product_id)).all()
			
			#print(result, "==")
			

			listOfSale = list()
			for i in result:
				mappedSale = mapper.mapSaleFromSQLAlchemy(i)
				listOfSale.append(mappedSale)
			session.commit()
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



if __name__ == '__main__':
	engine = sa.create_engine(f"sqlite+pysqlite:///./database/main.db")
	daoSale = DAOSale(engine)
	print("rows in sale table= ", len(daoSale.getAllSale()))
	res = daoSale.getAllSaleByProductId(uuid.UUID("d6b9e0b6-807d-452b-80b0-c89c5cd75aaf"))
	print(res)
	daoProduct = DAOProduct(engine)
	res2 = daoProduct.getAllProduct()
	print(len(res2))
	res3 = daoProduct.getProductById(uuid.UUID("d6b9e0b6-807d-452b-80b0-c89c5cd75aaf"))
	print(res3)
	res4 = daoProduct.getProductByName('"""O2 ACTIVE"", средство для дезинфекции воды бассейнов, 5л"')
	print(res4)