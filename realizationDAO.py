#
import sqlite3
import uuid

CREATE_REALIZATION_TABLE_QUERY = """
create table if not exists realization (
	id varchar(36) not null, 
	product_id varchar(36) not null,
	quantity int not null default 0,
	total double not null default 0,
	sale_month date not null,

	constraint realization_pk primary key (id),
	constraint realization_product_fk foreign key (product_id) references product (id),
	constraint realization_product_id_sale_month_unique unique(product_id,sale_month) 
)
"""
CREATE_PRODUCT_TABLE_QUERY = '''
create table if not exists product (
	id varchar(36) not null,
	name varchar(1024) not null,

	constraint product_pk primary key (id)
)
'''

class DAO:
	def __init__(self, databaseName):
		self.databaseName = databaseName
		self.con = sqlite3.connect(self.databaseName)
		self.sql = self.con.cursor()

	def saveProductToDb(self, productName)->str:
		QUERY = "insert into product (id,name) values (?,?)"
		productId = str(uuid.uuid4())
		self.sql.execute(QUERY, (productId, productName))
		return productId

	def saveToDb (self, realizationId, productId, quantity, total, saleMonth):
		QUERY= "insert into realization (id,product_id,quantity,total,sale_month) values (?,?,?,?,?)"
		self.sql.execute(QUERY, (realizationId,productId,quantity,total,saleMonth))

	def checkProductExist(self, productName)->str:
		QUERY = "select id from product where name=?"
		res = self.sql.execute(QUERY, (productName,))
		stringIntoProduct = res.fetchone()
		if stringIntoProduct == None:
			return None
		else:
			return stringIntoProduct[0] 

	def getProductIdOrCreate(self, productName)->str:
		productId = self.checkProductExist(productName)
		if productId == None:	
			productId = self.saveProductToDb(productName)
		return productId

	def commit(self):
		self.con.commit()

	def createTable(self):
		self.sql.execute(CREATE_REALIZATION_TABLE_QUERY)
		self.sql.execute(CREATE_PRODUCT_TABLE_QUERY)

	def getSalesByName(self, productName):
		QUERY = """select quantity,sale_month from realization where product_Id=? """
		productId = (self.getProductIdOrCreate(productName),)
		res = self.sql.execute(QUERY, productId)
		return res






if __name__ == "__main__":
	dao = DAO('moms22.db')
	dao.createTable()
	dao.saveToDb(11,22,33,44,'2022-11-12')
	dao.commit()




