# 
import pytest
import tempfile
import os
import uuid
from datetime import datetime

import sqlalchemy as sa
from sqlalchemy.orm import Session

import server.modules.DAO.DAOSQLAlchemy as al
import server.modules.DAO.ormmodel.ORMmodel as orm
from server.modules.adviserModel.product import Product
from server.modules.adviserModel.sale import Sale

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

	@pytest.mark.parametrize("param, result", [(None, sa.exc.IntegrityError)])
	def test_addProduct_negative(self, param, result):
		database = TemporaryFile()
		database.crateDatabase()
		try:
			engine = database.createEngine()
			orm.Base.metadata.create_all(engine)
			DAOProduct = al.DAOProduct(engine)
			with pytest.raises(result):
				newProduct = Product(product_id = (param), name = "firstProduct")
				result = DAOProduct.addProduct(newProduct)
		finally:
			database.removeDatabase()

	@pytest.mark.parametrize("param", [None, int(1), list(), Sale(uuid.uuid4(), uuid.uuid4(), 1, 2, datetime.today())])
	def test_addProduct_negative1(self, param):  #todo rebuild this test becouse of in future mapper can't accept other object 
		database = TemporaryFile()
		database.crateDatabase()
		try:
			engine = database.createEngine()
			orm.Base.metadata.create_all(engine)
			DAOProduct = al.DAOProduct(engine)
			with pytest.raises(AttributeError):
				result = DAOProduct.addProduct(param)
		finally:
			database.removeDatabase()

	def test_deleteProductById(self,):
		database = TemporaryFile()
		database.crateDatabase()
		try:
			engine = database.createEngine()
			orm.Base.metadata.create_all(engine)
			testId = uuid.UUID("06335e84-2872-4914-8c5d-3ed07d2a2f16")
			DAOProduct = al.DAOProduct(engine)
			newProduct = Product(product_id = (testId), name = "firstProduct")
			DAOProduct.addProduct(newProduct)
			result = DAOProduct.deleteProductById(newProduct)
			assert result ==  1

		finally:
			database.removeDatabase()

	def test_updateProductById(self,):
		database = TemporaryFile()
		database.crateDatabase()
		try:
			engine = database.createEngine()
			orm.Base.metadata.create_all(engine)
			testId = uuid.UUID("06335e84-2872-4914-8c5d-3ed07d2a2f16")
			DAOProduct = al.DAOProduct(engine)
			newProduct = Product(product_id = (testId), name = "firstProduct")
			DAOProduct.addProduct(newProduct)
			result = DAOProduct.updateProductById(newProduct)
			assert result ==  1
		finally:
			database.removeDatabase()

	def test_getAllProduct(self):
		database = TemporaryFile()
		database.crateDatabase()
		try:
			engine = database.createEngine()
			orm.Base.metadata.create_all(engine)
			testId = uuid.UUID("06335e84-2872-4914-8c5d-3ed07d2a2f16")
			testId2 = uuid.UUID("06335e84-2872-4914-8c5d-3ed07d2a2f15")
			DAOProduct = al.DAOProduct(engine)
			newProduct = Product(product_id = (testId), name = "firstProduct")
			newProduct2 = Product(product_id = (testId2), name = "secondProduct")
			DAOProduct.addProduct(newProduct)
			DAOProduct.addProduct(newProduct2)
			result = DAOProduct.getAllProduct()
			assert result[0].product_id == testId
			assert result[1].product_id == testId2
		finally:
			database.removeDatabase()


	def test_getProductById(self):
		database = TemporaryFile()
		database.crateDatabase()
		try:
			engine = database.createEngine()
			orm.Base.metadata.create_all(engine)
			testId = uuid.UUID("06335e84-2872-4914-8c5d-3ed07d2a2f16")
			DAOProduct = al.DAOProduct(engine)
			newProduct = Product(product_id = (testId), name = "firstProduct")
			DAOProduct.addProduct(newProduct)
			result = DAOProduct.getProductById(newProduct)
			assert result.product_id == testId
		finally:
			database.removeDatabase()

class TestDAOSQLAlchemySale:

	def test_create_DAO(self):
		database = TemporaryFile()
		database.crateDatabase()
		try:
			engine = database.createEngine()
			orm.Base.metadata.create_all(engine)

			DAOProduct = al.DAOSale(engine)
			assert type(DAOProduct) == al.DAOSale
		finally:
			database.removeDatabase()







		
