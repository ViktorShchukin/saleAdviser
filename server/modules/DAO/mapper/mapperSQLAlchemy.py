#

from server.modules.adviserModel.product import Product
from server.modules.adviserModel.sale import Sale
import server.modules.DAO.ormmodel.ORMmodel as orm

class MapperSQLAlchemy:

	def __init__ (self,):
		pass

	def mapProductToAlchemy(self, product: Product) -> orm.Product:
		saProduct = orm.Product(id = product.product_id, product_name = product.name)
		return saProduct

	def mapSaleToAlchemy(self, sale: Sale) -> orm.Sale:
		saSale = orm.Sale(id =sale.sale_id, product_id =sale.product_id, quantity =sale.quantity,
							total_value =sale.total_value, date =sale.date)

	def mapProductFromSQLAlchemy(self, product: orm.Product) -> Product:
		coreProduct = Product(product_id = product.id, name = product.product_name)
		return coreProduct
	