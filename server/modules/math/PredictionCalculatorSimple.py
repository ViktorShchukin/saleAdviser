#
from datetime import datetime

from server.modules.adviserModel.predictionResult import PredictionResult
from server.modules.math.PredictionCalculator import PredictionCalculator
from server.modules.math.dataMonipulation import TableFunction, Argument
from server.modules.DAO.DAOFactory import DAOFactory
from server.config.Config import Config


config_ = Config()
daoFactory = DAOFactory(config=config_)


class PredictionCalculatorSimple(PredictionCalculator):

	def __init__(self, productId):
		super().__init__(productId)
		# self.productId = productId
		# self.range_ = range_

	def initTableFunction(self,) -> TableFunction:
		sales = []
		date = []
		daoSale = daoFactory.getDAOSale()
		res = daoSale.getAllSaleByProductId(product_id=self.productId)
		for sale in res:
			sales.append(sale.quantity)
			date.append(Argument(year=sale.date.year, month=sale.date.month))
		return TableFunction(arg=date, func=sales)

	def predict(self, argument: Argument) -> PredictionResult:
		validatedArgument: Argument
		today = datetime.now()
		validatedArgument = Argument(year=today.year + argument.year, month=today.month + argument.month)
		tableFunction = self.initTableFunction()
		lastKnownArg = tableFunction.arg[len(tableFunction.arg) - 1]
		derivative = tableFunction.derivative(lastKnownArg)
		prediction = tableFunction.value(lastKnownArg) + derivative * validatedArgument.deltaMonth(lastKnownArg)
		return PredictionResult(productId=self.productId, value=prediction, range=argument.month)
		# todo все херня. Надо переделывать весь модуль math


