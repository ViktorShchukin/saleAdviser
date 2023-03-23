 

class Product:
	def __init__(self, productId, name):
		self.productId = productId
		self.name = name 

	def toJSON(self)-> dict:
		return {'productId': self.productId, 'name': self.name}