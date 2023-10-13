#
from server.config.Config import Config
from server.modules.math.PredictionCalculatorSimple import PredictionCalculatorSimple


class CalculatorFactory:

    def __init__(self, config: Config):
        self.config = config

    def getCalculator(self, product_id, calculatorType: str = None):  # todo сделать нормально
        if calculatorType is None:
            return PredictionCalculatorSimple(product_id)
        else:
            pass
