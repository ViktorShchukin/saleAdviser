#
import uuid
from datetime import datetime

from flask import Flask
# from flask import jsonify
from flask.json import dumps, loads
from flask import request
from flask import render_template
from flask import url_for
from flask import redirect

from server.config.Config import Config
from server.modules.DAO.DAOFactory import DAOFactory
from server.modules.adviserModel.product import Product
from server.modules.adviserModel.sale import Sale
from server.modules.math.PredictionCalculatorSimple import PredictionCalculatorSimple
from server.modules.math.calculatorFactory import CalculatorFactory
from server.modules.math.dataMonipulation import Argument


config = Config()
daoFactory = DAOFactory(config)
calculatorFactory = CalculatorFactory(config)

app = Flask(__name__)


@app.route('/', methods=['GET'])
def getIndex():
    # return redirect(url_for('static', filename='dist/index.html'))
    return render_template("dist/index.html")


@app.route('/dictionary/product', methods=['GET'])
def getProductAll():
    searchProduct = request.args.get("product_name")
    dao = daoFactory.getDAOProduct()
    if searchProduct is None:
        res = dao.getAllProduct()
        return dumps([e.toJSON() for e in res])
    else:
        # app.logger.info(f"{searchProduct}")
        res = dao.getProductByNameLike(searchProduct)
        # app.logger.info(f"{res}")
        return dumps([e.toJSON() for e in res])


@app.route('/dictionary/product/<productId>', methods=['GET'])
def getProductById(productId):
    dao = daoFactory.getDAOProduct()
    product = dao.getProductById(uuid.UUID(productId))
    if product is None:
        return "", 404
    else:
        return dumps(product.toJSON())


@app.route('/dictionary/product/', methods=['POST'])
def createNewProduct():
    row = loads(request.data)
    dao = daoFactory.getDAOProduct()
    newProduct = Product(product_id=uuid.uuid4(), name=row['name'])
    product = dao.addProduct(newProduct)
    if product is None:
        return "", 404  # find more fit http code
    else:
        return dumps(product.toJSON())


@app.route('/dictionary/product/<productId>', methods=['PUT'])
def updateProduct(productId):
    row = loads(request.data)
    dao = daoFactory.getDAOProduct()
    product = dao.getProductById(uuid.UUID(productId))
    if product is None:
        return "", 404
    rebuildingProduct = Product(product_id=uuid.UUID(productId), name=row['name'])
    dao.updateProduct(rebuildingProduct)
    product1 = dao.getProductById(uuid.UUID(productId))
    if product1 is None:
        return "", 404
    else:
        return dumps(product1.toJSON())


@app.route('/dictionary/product/<productId>', methods=['DELETE'])
def deleteProduct(productId):
    dao = daoFactory.getDAOProduct()
    res1 = dao.getProductById(uuid.UUID(productId))
    if res1 is None:
        return "", 404
    res = dao.deleteProductById(uuid.UUID(productId))
    if res == 1:
        return "", 201


@app.route('/dictionary/product/<productId>/sale', methods=['GET'])
def getAllSales(productId):
    dao = daoFactory.getDAOSale()
    sales = dao.getAllSaleByProductId(uuid.UUID(productId))
    # app.logger.info(f"{sales}")
    if sales is None:
        return "", 404
    else:
        return dumps([e.toJSON() for e in sales])


# todo it was a mistake. need to change this on POST method
""" 
@app.route('/dictionary/product/<productId>/sale', methods=['GET'])
def getSalesById(productId):
    dao = DAOFactory.getDAOSale()
    sale = dao.getAllSaleByProductId(uuid.UUID(productId))
    
    if sale is None:
        return "", 404
    else:
        return dumps(sale.toJSON())
"""


@app.route('/dictionary/product/<productId>/sale/<saleId>', methods=['GET'])
def createNewSale(productId, saleId):
    dao = daoFactory.getDAOSale()
    sales = dao.getSaleBySaleId(uuid.UUID(saleId))

    if sales is None:
        return "", 404
    else:
        return dumps([e.toJSON() for e in sales])


@app.route('/dictionary/product/<productId>/sale/<saleId>', methods=['PUT'])
def updateSale(productId, saleId):  # переделать порядок передачи переменных todo
    row = loads(request.data)
    dao = daoFactory.getDAOSale()
    sale = dao.getSaleBySaleId(uuid.UUID(saleId))
    # ISOdate = datetime.strptime(row['saleMonth'], '%d.%m.%Y')
    date = datetime.fromisoformat(row['saleMonth'])
    if sale is None:
        return "", 404
    rebuildingSale = Sale(sale_id=uuid.UUID(saleId), product_id=uuid.UUID(productId), quantity=row['quantity'],
                          total_value=row['total'], date=date)
    dao.updateSale(rebuildingSale)
    sale1 = dao.getSaleBySaleId(rebuildingSale.sale_id)
    if sale1 is None:
        return "", 404
    else:
        return dumps(sale.toJSON())


@app.route('/dictionary/product/<productId>/sale/<saleId>', methods=['DELETE'])
def deleteSale(productId, saleId):
    dao = daoFactory.getDAOSale()
    sale1 = dao.getSaleBySaleId(uuid.UUID(saleId))
    if sale1 is None:
        return "", 404
    res = dao.deleteSaleById(uuid.UUID(saleId))
    if res == 1:
        return "", 201  # find more fit http code


# todo create a normal prediction architecture
@app.route('/dictionary/product/<productId>/prediction/<range_>', methods=['GET'])
def getPrediction(productId, range_):
    calculator = calculatorFactory.getCalculator(productId)
    # todo recreate argument in predict method
    arg_: Argument = None
    if range_ == "30":
        arg_ = Argument(0, 1)
    result = calculator.predict(argument=arg_)
    # todo toJson return wrong things, refactor it
    return dumps(result.toJson())


