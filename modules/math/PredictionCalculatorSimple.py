#

class PredictionCalculatorSimple (PredictionCalculator):

	def __init__ (self, productId, tableFunction: TableFunction):
		super(PredictionCalculatorSimple,self).__init__(productId, tableFunction)

	def predict(self, argument: Argument)-> PredictionResult:
		pass #return predictionResult

