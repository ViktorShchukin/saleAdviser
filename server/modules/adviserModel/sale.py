#

import uuid
from datetime import datetime

class Sale:
	def __init__(self, sale_id: uuid.UUID, product_id: uuid.UUID, quantity: int, total_value: int , date: datetime):
		self.sale_id = sale_id
		self.product_id = product_id
		self.quantity = quantity
		self.total_value = total_value
		self.date = date

	def toJSON(self) -> dict:
		return {'sale_id':self.sale_id, 'product_id':self.product_id, 'quantity':self.quantity,
				 'total_value':self.total_value, 'date':self.saleMonth}