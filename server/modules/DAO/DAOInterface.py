#

import uuid

from server.modules.adviserModel.product import Product
from server.modules.adviserModel.sale import Sale



class DAOProduct:
	"""standart interface for DAOProduct object"""

	def addProduct(self, product: Product) -> uuid.UUID:#todo return new product object
		#return productId
		pass

	def deleteProductById(self, product: Product) -> int:
		#return кол-во удаленных кортежей 
		pass

	def updateProductById(self, product: Product) -> int:
		pass

	def getAllProduct(self, ) -> list:
		#return List(Product)
		pass

	def getProductById(self, product: Product) -> Product:
		#return Product
		pass
		

	def checkProductExistByName(self, product: Product) -> uuid.UUID:
		#return productId
		pass


class DAOSale:
	"""standart interface for DAOSale object"""
	
	def addSaleByProductName(self, product: Product, sale: Sale) -> uuid.UUID:
		#return saleId
		pass

	def deleteSaleByProductNameAndSaleId(self, product: Product, sale: Sale) -> int:
		#return кол-во удаленных кортежей 
		pass

	def updateSaleByProductNameAndSaleId(self, product: Product, sale: Sale) -> int:
		#return 
		pass

	def getAllSale(self, product: Product) -> list:
		#return List(Sale)
		pass

	def getSaleByProductNameAndSaleId(self, product: Product, sale: Sale) -> Sale:
		#return Sale
		pass

	def checkSaleExistByProductNameAndDate(self, product: Product, sale: Sale) -> uuid.UUID:
		#return saleId
		pass
