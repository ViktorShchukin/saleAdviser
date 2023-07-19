#

import server.config.Config as cn
import server.modules.DAO.DAOFactory as fa





class TestDAOFactory:
	
	def test_return_DAO(self):
		self.config = cn.Config()
		self.factory = fa.DAOFactory(self.config)
		assert type(self.factory) == fa.DAOFactory