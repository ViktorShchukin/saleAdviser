#

import uuid

from server.modules.adviserModel.product import Product
from server.modules.adviserModel.sale import Sale



class DAOProduct:
	"""standart interface for DAOProduct object"""

	def addProduct(self, product: Product) -> Product:#todo return new product object
		#return productId
		pass

	def deleteProduct(self, product: Product) -> int:
		#return кол-во удаленных кортежей 
		pass

	def updateProduct(self, product: Product) -> int:
		pass

	def getAllProduct(self, ) -> list:
		#return List(Product)
		pass

	def getProductById(self, product_id: uuid.UUID) -> Product:
		#return Product
		pass
		

	def checkProductExistByName(self, productName: str) -> bool:
		#return productId
		pass


class DAOSale:
	"""standart interface for DAOSale object"""
	
	def addSale(self, sale: Sale) -> uuid.UUID:
		#return saleId
		pass

	def deleteSale(self, sale: Sale) -> int:
		#return кол-во удаленных кортежей 
		pass

	def updateSale(self, sale: Sale) -> int:
		#return 
		pass

	def getAllSale(self,) -> list:
		#return List(Sale)
		pass

	def getAllSaleByProductId(self, product_id: uuid.UUID) -> list:
		#return Sale
		pass

	def checkSaleExistByProductNameAndDate(self, product: Product, sale: Sale) -> uuid.UUID:
		#return saleId
		pass
