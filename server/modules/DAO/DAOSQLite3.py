#
import uuid
import sqlite3
from datetime import datetime, date

from server.modules.adviserModel.product import Product
from server.modules.adviserModel.sale import Sale
from server.modules.DAO.DAOInterface import DAOProductBase, DaoSaleBase


class DAOProduct(DAOProductBase):

    def __init__(self, databaseName: str) -> None:
        self.databaseName = databaseName
        self.con = sqlite3.connect(self.databaseName)
        self.sql = self.con.cursor()

    def addProduct(self, product: Product) -> Product:
        QUERY = "insert into product (id,product_name) values (?,?)"
        self.sql.execute(QUERY, (product.product_id, product.name))
        newProduct = Product(product_id=product.product_id, name=product.name)
        return newProduct

    def deleteProduct(self, product: Product) -> int:
        QUERY = "delete from product where id=? "
        res = self.sql.execute(QUERY, (product.product_id,))
        return res.rowcount

    def deleteProductById(self, product_id) -> int:
        id_ = ""
        if type(product_id) == uuid.UUID:
            id_ = str(product_id)
        else:
            id_ = product_id
        QUERY = """delete from product where id=? """
        res = self.sql.execute(QUERY, (id_,))
        return res.rowcount

    def updateProduct(self, product: Product) -> int:
        QUERY = """ update product set product_name=? where id=?"""
        res = self.sql.execute(QUERY, (product.name, product.product_id))
        return res.rowcount

    def getAllProduct(self) -> list:
        QUERY = "select * from product"
        res = self.sql.execute(QUERY)
        row = res.fetchone()
        products = list()
        while row is not None:
            product = Product(row[0], row[1])
            products.append(product)
            row = res.fetchone()
        return products

    def getProductById(self, product_id) -> Product:
        id_ = ""
        if type(product_id) == uuid.UUID:
            id_ = str(product_id)
        else:
            id_ = product_id
        QUERY = "select * from product where id=?"
        res = self.sql.execute(QUERY, (id_,))
        row = res.fetchone()
        product = Product(product_id=uuid.UUID(row[0]), name=row[1])
        return product

    def checkProductExistByName(self, productName: str) -> bool:
        return super().checkProductExistByName(productName)

    def getProductByNameLike(self, searchProduct: str) -> list[Product]:
        newSearchProduct = "%" + searchProduct + "%"
        QUERY = "select * from product where product_name like ?"
        res = self.sql.execute(QUERY, (newSearchProduct,))
        row = res.fetchone()
        products = list()
        while row is not None:
            product = Product(product_id=uuid.UUID(row[0]), name=row[1])
            products.append(product)
            row = res.fetchone()
        return products


class DAOSale(DaoSaleBase):

    def __init__(self, databaseName: str) -> None:
        self.databaseName = databaseName
        self.con = sqlite3.connect(self.databaseName)
        self.sql = self.con.cursor()

    def addSale(self, sale: Sale) -> Sale:
        QUERY = "insert into sale (id,product_id,quantity,total_value,date) values (?,?,?,?,?)"
        self.sql.execute(QUERY, (sale.sale_id, sale.product_id,
                                 sale.quantity, sale.total_value, sale.date))
        newSale = Sale(sale.sale_id, sale.product_id,
                       sale.quantity, sale.total_value, sale.date)
        return newSale

    def deleteSale(self, sale: Sale) -> int:
        return super().deleteSale(sale)

    def deleteSaleById(self, sale_id: uuid.UUID) -> int:
        return super().deleteSaleById(sale_id)

    def updateSale(self, sale: Sale) -> int:
        return super().updateSale(sale)

    def getAllSale(self) -> list:
        return super().getAllSale()

    def getAllSaleByProductId(self, product_id) -> list[Sale]:
        id_ = ""
        if type(product_id) == uuid.UUID:
            id_ = str(product_id)
        else:
            id_ = product_id
        QUERY = "select * from sale where product_id=?"
        res = self.sql.execute(QUERY, (id_, ))
        row = res.fetchone()
        sales = list()
        while row is not None:
            sale = Sale(uuid.UUID(row[0]), uuid.UUID(row[1]), int(row[2]), int(row[3]), datetime.fromisoformat(row[4]))
            sales.append(sale)
            row = res.fetchone()
        return sales

    def checkSaleExistByProductNameAndDate(self, product: Product, sale: Sale) -> bool:
        return super().checkSaleExistByProductNameAndDate(product, sale)

    def getSaleBySaleId(self, sale_id: uuid.UUID) -> Sale:
        return super().getSaleBySaleId(sale_id)
