#


class PredictionResult:
	def __init__(self, productId, value, range):
		self.productId = productId
		self.value = value
		self.range = range

	def getProductId(self):
		return self.productId

	def getValue(self):
		return self.value

	def toJson(self) -> dict:
		return {'product_id': self.productId, 'value': self.value, 'range': self.range}
