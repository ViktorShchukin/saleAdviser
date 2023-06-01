#



class Config:
	def __init__(self, file ):
		self.config = {"connectionString":"sqlite+pysqlite:///database/main.db ", "databaseName":"SQLite3"}
		#read file and init param 
		pass

	def getParam(self, paramName: str) -> str:
		return self.config[paramName]