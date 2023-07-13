# 
import pytest
import tempfile
import os
import uuid

import sqlalchemy as sa
from sqlalchemy.orm import Session

import server.modules.DAO.DAOSQLAlchemy as al
import server.modules.DAO.ORMmodel as orm

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


			

		finally:
			database.removeDatabase()

		 
		#DAOProduct = al.DAOProduct(self.engine)
		#assert type(DAOProduct) == al.DAOProduct

		#DAOSale = al.DAOSale(self.engine)
		#assert type(DAOSale) == al.DAOSale
		
	#@pytest.mark.parametrize("param, result", [()])
	def test_addProduct(self, param, result):
		#DAOProduct = al.DAOProduct(self.engine)
		#result = DAOProduct.addProduct(param)
		pass 


	
test = TestDAOSQLAlchemyProduct()
test.test_create_DAO()