#


class PredictionResult :
	def __init__(self, productId, value):
		self.productId = productId
		self.value = value

	def getProductId(self):
		return self.productId

	def getValue(self):
		return self.value


