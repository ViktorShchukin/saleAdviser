#

from server.modules.adviserModel.product import Product
from server.modules.adviserModel.sale import Sale
import server.modules.DAO.ormmodel.ORMmodel as orm

class MapperProduct:

	def __init__ (self, product: Product):
		self.product = product

	def toSQLAlchemy(self,) -> orm.Product:
		self.saProduct = orm.Product(id = self.product.product_id, product_name = self.product.name)
		return self.saProduct

	#todo reorganaise mapper class 