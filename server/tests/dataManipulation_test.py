#
import pytest

from server.modules.math.dataMonipulation import TableFunction, Argument
from server.config.Config import Config
from server.modules.DAO.DAOFactory import DAOFactory

config = Config()
daoFactory = DAOFactory(config)


class TestArgument:
    def test_Argument(self):
        november22 = Argument(2022, 10)
        junary23 = Argument(2023, 1)
        gap = junary23.deltaMonth(november22)
        reversGap = november22.deltaMonth(junary23)
        gap2 = junary23 - november22
        reversGap2 = november22 - junary23
        gap3 = junary23 + november22
        reversGap3 = november22 + junary23
        if gap != 3:
            raise RuntimeError('gap!=3')
        if reversGap != -3:
            raise RuntimeError('gap!=-3')
        if gap2 != Argument(0, 3):
            raise RuntimeError('gap2!=3')
        if reversGap2 != Argument(0, -3):
            raise RuntimeError('gap2!=-3')
        if gap3 != Argument(4045, 11):
            raise RuntimeError('gap3 != 4045-11')

    def test_Argument0(self):
        audust22 = Argument(2022, 8)
        audust22_1 = Argument(2022, 8)
        gap2 = audust22 - audust22_1
        if gap2 != Argument(0, 0):
            raise RuntimeError('gap2!=0')


class TestTableFunction:

    @staticmethod
    def initTableFunction() -> TableFunction:
        """
        function to create a TableFunction object
        """
        # productId = "d6b9e0b6-807d-452b-80b0-c89c5cd75aaf"
        sales = [2, 4, 1, 25, 75]
        date = [Argument(2021, 1),
                Argument(2021, 2),
                Argument(2021, 3),
                Argument(2021, 4),
                Argument(2021, 6)
                ]

        return TableFunction(arg=date, func=sales)

    @pytest.mark.parametrize("param, result", [(Argument(2021, 1), 2),
                                               (Argument(2021, 3), 1),
                                               (Argument(2021, 4), 25),
                                               (Argument(2020, 12), 2),
                                               (Argument(2023, 1), 75),
                                               (Argument(2021, 5), 50)
                                               ])
    def test_value_method_positive(self, param, result):
        """
        firs 3 parameters check the return of existing inside function value

        next 2 parameters check what if pass Argument that is out of range of function value
        it should return the first and the last function value

        and last parameter check what return if pass Argument that between two function value
        it should return approximate value
        """
        table_function: TableFunction
        table_function = self.initTableFunction()
        res = table_function.value(param)
        assert res == result

    def test_value_method_negative(self):  # todo
        table_function: TableFunction
        table_function = self.initTableFunction()
        pass

    @pytest.mark.parametrize("param, result", [(Argument(2021, 6), 25),
                                               (Argument(2021, 4), 24)
                                               ])
    def test_derivative_method_positive(self, param, result):
        table_function: TableFunction
        table_function = self.initTableFunction()
        res = table_function.derivative(param)
        assert res == result

    def test_calculator(self):
        table_function: TableFunction
        table_function = self.initTableFunction()
