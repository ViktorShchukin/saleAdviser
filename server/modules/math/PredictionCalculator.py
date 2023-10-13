#
from server.modules.adviserModel.predictionResult import PredictionResult
from server.modules.math.dataMonipulation import TableFunction, Argument


class PredictionCalculator:

	def __init__(self, productId):
		self.productId = productId

	def predict(self, argument: Argument) -> PredictionResult:
		pass

