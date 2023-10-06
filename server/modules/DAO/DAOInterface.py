#

import uuid

from server.modules.adviserModel.product import Product
from server.modules.adviserModel.sale import Sale


class DAOProductBase:
	"""standard interface for DAOProduct object"""

	def addProduct(self, product: Product) -> Product:  # todo return new product object
		"""
		return new Product object
		"""
		pass

	def deleteProduct(self, product: Product) -> int:
		"""
		return number of deleted rows
		"""
		pass

	def deleteProductById(self, product_id: uuid.UUID) -> int:
		"""
		return number of deleted rows
		"""
		pass

	def updateProduct(self, product: Product) -> int:
		"""
		return number of updated rows
		"""
		pass

	def getAllProduct(self, ) -> list:
		"""
		return List(Product)
		"""
		pass

	def getProductById(self, product_id: uuid.UUID) -> Product:
		"""
		return Product object
		"""
		pass

	def checkProductExistByName(self, productName: str) -> bool:
		"""
		return Bool value
		"""
		pass

	def getProductByNameLike(self, searchProduct: str) -> list[Product]:
		"""
		return Product object
		"""
		pass


class DaoSaleBase:
	"""standard interface for DAOSale object"""
	
	def addSale(self, sale: Sale) -> Sale:
		"""
		return new Sale object
		"""
		pass

	def deleteSale(self, sale: Sale) -> int:
		"""
		return number of deleted rows
		"""
		pass

	def deleteSaleById(self, sale_id: uuid.UUID) -> int:
		"""
		return number  of deleted rows
		"""
		pass

	def updateSale(self, sale: Sale) -> int:
		"""
		return number of updated rows
		"""
		pass

	def getAllSale(self,) -> list:
		"""
		return List(Sale)
		"""
		pass

	def getAllSaleByProductId(self, product_id: uuid.UUID) -> list:
		"""
		return List(Sale)
		"""
		pass

	def checkSaleExistByProductNameAndDate(self, product: Product, sale: Sale) -> bool:
		"""
		return Bool value
		"""
		pass

	def getSaleBySaleId(self, sale_id: uuid.UUID) -> Sale:
		"""
		return new Sale object
		"""
		pass
