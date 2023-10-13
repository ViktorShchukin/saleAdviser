#

import pytest

import server.config.Config as cn

@pytest.mark.parametrize("param, result", [("connectionString", "sqlite+pysqlite:///database/main.db"),
											("databaseName", "SQLite3")])
def test_config_getParam(param, result):
	"""
	in future config object will accept config.json
	we need to parametrize different types of files and check behavior of object 

	@pytest.mark.parametrize("file", [("f.xml"), ("f.json"), ("f.txt"), (None)])
	def test_config(file):

	and we need to do it singletone and check 

	"""
	conf = cn.Config()
	assert conf.getParam(param) == result
	

