#
from flask import Flask
from flask import jsonify
from flask.json import dumps, loads
from flask import request
import modules.DAO.realizationDAO as realizationDAO


app = Flask(__name__)

@app.route('/dictionary/product',  methods=['GET'])
def getProductAll():
    dao = realizationDAO.DAO('moms1.db')
    res = dao.getProductAll()
    return dumps([e.toJSON() for e in res])

@app.route('/dictionary/product/<productId>',  methods=['GET'])
def getProductById(productId):
    dao = realizationDAO.DAO('moms1.db')
    product = dao.getProductById(productId)
    if product == None:
        return "", 404
    else:
        return dumps(product.toJSON())
    
@app.route('/dictionary/product/',  methods=['POST'])
def createNewProduct():
    row = loads(request.data)
    dao = realizationDAO.DAO('moms1.db')
    newProductId = dao.saveProductToDb(row['name'])
    dao.commit()
    product = dao.getProductById(newProductId)
    if product == None:
        return "", 404 #стоит ли возвращать код 500?
    else:
        return dumps(product.toJSON())

@app.route('/dictionary/product/<productId>',  methods=['PUT'])
def updateProduct(productId):
    row = loads(request.data)
    dao = realizationDAO.DAO('moms1.db')
    product = dao.getProductById(productId)
    if product == None:
        return "", 404
    product.name = row['name'] 
    dao.updateProduct(product)
    product = dao.getProductById(product.productId)
    dao.commit()
    if product == None:
        return "", 404
    else:
        return dumps(product.toJSON())

@app.route('/dictionary/product/<productId>',  methods=['DELETE'])
def deleteProduct(productId):
    dao = realizationDAO.DAO('moms1.db')
    res = dao.deleteProduct(productId)
    dao.commit()
    if res == 0:
        return "", 404
    else:
        return "", 201

@app.route('/dictionary/product/<productId>/sale',  methods=['GET'])
def getAllSales(productId):
    dao = realizationDAO.DAO('moms1.db')
    sales = dao.getAllSalesByProductId(productId)
    if sales == None:
        return "", 404
    else:
        return dumps([e.toJSON() for e in sales])

@app.route('/dictionary/product/<productId>/sale',  methods=['POST'])
def createNewSale(productId):
    row = loads(request.data)
    dao = realizationDAO.DAO('moms1.db')
    newSaleId = dao.saveToDb(row['productId'], row['quantity'], row['total'], row['saleMonth'])
    dao.commit()
    sale = dao.getSaleById(productId, newSaleId)
    if sale == None:
        return "", 404
    else:
        return dumps(sale.toJSON())
    
  
@app.route('/dictionary/product/<productId>/sale/<saleId>',  methods=['PUT'])
def updateSale(productId, saleId):#переделать порядок передачи переменных todo
    row = loads(request.data)
    dao = realizationDAO.DAO('moms1.db')
    sale = dao.getSaleById(productId, saleId)
    if sale == None:
        return "", 404
    sale.productId = row['productId'] 
    sale.quantity = row['quantity']
    sale.total = row['total']
    sale.saleMonth = row['saleMonth']
    dao.updateSale(sale)
    sale = dao.getSaleById(sale.productId, saleId)
    dao.commit()
    if sale == None:
        return "", 404
    else:
        return dumps(sale.toJSON())

@app.route('/dictionary/product/<productId>/sale/<saleId>',  methods=['DELETE'])
def deleteSale(productId, saleId):
    dao = realizationDAO.DAO('moms1.db')
    res = dao.deleteSale(productId, saleId)
    dao.commit()
    if res == 0:
        return "", 404
    else:
        return "", 201