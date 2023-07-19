# 
import modules.DAO.realizationDAO as realizationDAO


def createTableFunc (dao, productName: str) :
	sales = []
	date = []
	res = dao.getSalesByName(productName)
	row = res.fetchone()
	while row != None :
		#print (row,'#')
		parseDate = row[1].split(' ')
		parseDate = parseDate[0].split('-')
		dateObject = Argument(int(parseDate[0]), int(parseDate[1]))
		#print(f'==========={dateObject}')
		sales.append(row[0])
		print('sales = ', sales)
		date.append(dateObject)
		#print(date)
		row = res.fetchone()
	return sales, date
	

class TableFunction :
	def __init__(self, arg, func) :
		self.arg = arg
		self.f = func

	def value(self, x) :
		result = 0
		if x <= self.arg[0]:
			result = self.f[0]
		elif x >= self.arg[len(self.arg) - 1] :
			result = self.f[len(self.arg) - 1]
		else :
			for i in range(len(self.arg) - 1) :
				if x == self.arg[i] :
					result = self.f[i]
					break 
				else :
					if x > self.arg[i] and x < self.arg[i + 1] :
						result = self.f[i] + (self.f[i+1]-self.f[i]) * (x - self.arg[i]) / (self.arg[i+1] - self.arg[i])
						break 
		return result		

	def derivative(self, arg0) :
		func0 = self.value(arg0)
		arg1 = arg0 - 1
		print ('вычитание внитри дериватива =', arg1)
		func1 = self.value(arg1)
		derivative = (func1 - func0) / (arg1.deltaMonth(arg0))
		return derivative #todo доделать 

class Argument :
	def __init__(self, year: int, month: int):
		self.year = year
		self.month = month
		#make specification for input data todo
	def __add__ (self, o):
		return self.add(o)

	def __sub__(self, o):
		return self.minus(o)
		#todo проверять тип входа, есть встроенная функция 

	def __gt__ (self, o):
		return self.greaterThan(o)

	def __ge__ (self, o):
		return self.greaterThanOrEqual(o)

	def __lt__ (self, o):
		return self.lessThan(o)

	def __le__ (self, o):
		return self.lessThanOrEqual(o)

	def __eq__ (self, o):
		return self.equal(o)

	def __ne__ (self, o):
		return self.notEqual(o)

	def __str__ (self):
		return f"{self.year}-{self.month}"

	def add(self, o) :
		if type(o) == int:
			res = self.month + o
			year = res // 12
			year1 = self.year + year 
			month1 = res % 12
			return Argument(year1, month1)
		else:
			res = self.month + o.month
			year = res // 12
			year1 = self.year + o.year + year 
			month1 = res % 12
			return Argument(year1, month1)

	def minus(self, o) :
		if type(o) == int:
			res = self.year*12 + self.month - o
			year1 = res // 12
			month1 = res % 12
			return Argument(year1, month1)
		else:
			deltaYear = self.year - o.year
			if deltaYear == 0:
				deltaMonth = self.month - o.month
				return Argument(deltaYear, deltaMonth)
			else:
				deltaMonth = deltaYear*12 + self.month - o.month
				year1 = deltaMonth // 12
				month1 = deltaMonth % 12
				return Argument(year1, month1)

	def deltaMonth(self, o)-> int:
		if type(o) == int:
			deltaMonth = self.year*12 + self.month - o
		else:
			deltaYear = self.year - o.year
			if deltaYear == 0:
				deltaMonth = self.month - o.month
			else:
				deltaMonth = deltaYear*12 + self.month - o.month
			return deltaMonth


	def greaterThan(self, o) -> bool:
		if self.deltaMonth(o) > 0:
			return True
		else:
			return False

	def lessThan(self, o) -> bool:
		if self.deltaMonth(o) < 0:
			return True
		else:
			return False

	def greaterThanOrEqual(self, o) -> bool:
		if self.deltaMonth(o) >= 0:
			return True 
		else:
			return False

	def lessThanOrEqual(self, o) -> bool:
		if self.deltaMonth(o) <= 0:
			return True 
		else:
			return False

	def equal(self, o) -> bool:
		if self.deltaMonth(o) == 0:
			return True
		else:
			return False

	def notEqual(self, o) -> bool:
		if self.deltaMonth(o) != 0:
			return True
		else:
			return False


def testArgument() :
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

def testArgument0() :
	audust22 = Argument(2022, 8)
	audust22_1 = Argument(2022, 8)
	gap2 = audust22 - audust22_1
	if gap2 != Argument(0, 0):
		raise RuntimeError('gap2!=0')

def testTableFunctionValueMethod () :
	sales, date = createTableFunc(dao, 'Аксессуары')
	tableFuncJunApr = TableFunction(date, sales)
	may21 = Argument(2021, 5)
	october20 = Argument(2020, 10)
	if tableFuncJunApr.value(may21) != 71:
		raise RuntimeError('ошибка в рассчетах табличной функции1')
	if tableFuncJunApr.value(october20) != 19:
		raise RuntimeError('ошибка в рассчетах табличной функции2')
		
def testTableFunctionDerivativeMethod () :
	sales, date = createTableFunc(dao, 'Аксессуары')
	tableFuncJunApr = TableFunction(date, sales)
	april21 = Argument (2021, 4)
	march21 = Argument (2021, 3)
	print (tableFuncJunApr.derivative(march21))
	print (tableFuncJunApr.derivative(april21))

	pass

def lagrangePolinomial (sales, date) :
	pass #todo


if __name__ == "__main__" :
	
	dao = realizationDAO.DAO('moms1.db')

	testArgument ()

	testArgument0 ()

	testTableFunctionValueMethod ()

	testTableFunctionDerivativeMethod ()

	#readAboutHowToDoTests todo
	#pip(done) прочитать про утилиту и env(собирать проект) 
	#
	
	