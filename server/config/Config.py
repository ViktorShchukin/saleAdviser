#



class Config:
	def __init__(self, file = None):
		self.config = {"connectionString":"sqlite+pysqlite:///database/main.db", "databaseName":"SQLite3"}
		#todo read file and init param
		#todo need to be singeltone
		
	def getParam(self, paramName: str) -> str:
		return self.config[paramName]

