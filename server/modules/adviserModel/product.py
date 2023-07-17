#

import uuid

class Product:
	"""
	class wich describe product unit
	this class should to be use in interior logic of server app
	"""

	def __init__(self, product_id: uuid.UUID, name: str):
		self.product_id = product_id
		self.name = name 

	def toJSON(self)-> dict:
		return {'product_id': self.product_id, 'name': self.name}