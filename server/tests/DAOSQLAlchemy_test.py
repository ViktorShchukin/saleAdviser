# 
import pytest
import tempfile
import os
import uuid

import sqlalchemy as sa
from sqlalchemy.orm import Session

import server.modules.DAO.DAOSQLAlchemy as al
import server.modules.DAO.ormmodel.ORMmodel as orm
from server.modules.adviserModel.product import Product

class TemporaryFile:

	def __init__(self, ):
		pass
		

	def crateDatabase(self, ):
		self.DatabasePath = tempfile.mkstemp(suffix = ".db")

	def createEngine(self, ):
		self.engine = sa.create_engine(f"sqlite+pysqlite:///{self.DatabasePath[1]}")
		return self.engine

	def removeDatabase(self, ):
		self.engine.dispose()
		os.close(self.DatabasePath[0])
		os.remove(self.DatabasePath[1])
		

class TestDAOSQLAlchemyProduct:

	def test_create_DAO(self):
		database = TemporaryFile()
		database.crateDatabase()
		try:
			engine = database.createEngine()
			orm.Base.metadata.create_all(engine)

			DAOProduct = al.DAOProduct(engine)
			assert type(DAOProduct) == al.DAOProduct
		finally:
			database.removeDatabase()

		 
		
		#DAOSale = al.DAOSale(self.engine)
		#assert type(DAOSale) == al.DAOSale
		
	#@pytest.mark.parametrize("param, result", [()])
	def test_addProduct_positive(self, ):
		database = TemporaryFile()
		database.crateDatabase()
		try:
			engine = database.createEngine()
			orm.Base.metadata.create_all(engine)
			testId = uuid.UUID("06335e84-2872-4914-8c5d-3ed07d2a2f16")
			DAOProduct = al.DAOProduct(engine)
			newProduct = Product(product_id = (testId), name = "firstProduct")
			result = DAOProduct.addProduct(newProduct)
			assert str(result) ==  "06335e84-2872-4914-8c5d-3ed07d2a2f16"

		finally:
			database.removeDatabase()


		#DAOProduct = al.DAOProduct(self.engine)
		#result = DAOProduct.addProduct(param)
		


	
#test22 = TestDAOSQLAlchemyProduct()
#test22.test_create_DAO()