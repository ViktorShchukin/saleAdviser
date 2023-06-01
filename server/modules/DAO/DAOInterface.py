from server.modules.DAO.ORMmodel import Product, Sale
import uuid






class DAOProduct:
	"""todo сделать документацию к классу и методам"""

	def addProduct(self, productName: str) -> uuid.UUID:
		#return productId
		pass

	def deleteProductById(self, productId: uuid.UUID) -> int:
		#return кол-во удаленных кортежей 
		pass

	def updateProductById(self, productId: uuid.UUID) -> int:
		pass

	def getAllProduct(self, ) -> list:
		#return List(Product)
		pass

	def getProductById(self, productId: uuid.UUID) -> Product:
		#return Product
		pass
		

	def checkProductExistByName(self, productName: str) -> uuid.UUID:
		#return productId
		pass


class DAOSale:
	"""todo сделать документацию к классу и методам"""
	
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
		#return Sale
		pass

	def checkSaleExistByProductNameAndDate(self, productName: str, saleDate: uuid.UUID) -> uuid.UUID:
		#return saleId
		pass
