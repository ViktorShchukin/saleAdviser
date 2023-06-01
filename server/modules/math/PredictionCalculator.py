#
from modules.adviserModel.PredictionResult import PredictionResult
from modules.math.dataMonipulation import TableFunction, Argument

class PredictionCalculator:

	def __init__ (self, productId, tableFunction: TableFunction):
		self.productId = productId
		self.tableFunction = tableFunction

	def predict(self, argument: Argument)-> PredictionResult:
		pass

