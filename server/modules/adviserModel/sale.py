

class Sale:
	def __init__(self, saleId, productId, quantity, total, saleMonth):
		self.saleId = saleId
		self.productId = productId
		self.quantity = quantity
		self.total = total
		self.saleMonth = saleMonth

	def toJSON(self):
		return {'saleId':self.saleId, 'productId':self.productId, 'quantity':self.quantity, 'total':self.total, 'saleMonth':self.saleMonth}