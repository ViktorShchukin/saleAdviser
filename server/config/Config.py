#


class Config:
    def __init__(self, file=None):
        self.config = {"connectionString": "sqlite+pysqlite:///database/main.db",
                       "databaseName": "SQLite3",
                       "path": "./database/main.db"
                       }

    # todo read file and init param
    # todo need to be singleton

    def getParam(self, paramName: str) -> str:
        return self.config[paramName]
