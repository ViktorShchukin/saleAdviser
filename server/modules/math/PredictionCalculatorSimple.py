#

class PredictionCalculatorSimple (PredictionCalculator):

	def __init__ (self, productId, tableFunction: TableFunction):
		super(PredictionCalculatorSimple,self).__init__(productId, tableFunction)

	def predict(self, argument: Argument)-> PredictionResult:
		lastKnownArg = tableFunction.arg[len(tableFunction.arg)]
		derivative = self.tableFunction.derivative(lastKnownArg)
		prediction = self.tableFunction.value(lastKnownArg) + derivative*(argument - lastKnownArg)
		pass #return predictionResult

