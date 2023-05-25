from sqlalchemy.orm import DeclarativeBase
from sqlalchemy.orm import Session
from typing import List
from typing import Optional
from sqlalchemy.orm import Mapped
from sqlalchemy.orm import mapped_column
from sqlalchemy.orm import relationship
import uuid
import sqlalchemy as db
from datetime import datetime 

engine = db.create_engine("sqlite+pysqlite:///testDAO.db")

class Base(DeclarativeBase):
	pass

class Product(Base):
	__tablename__ = "products"

	id: Mapped[uuid.UUID] = mapped_column(db.Uuid, primary_key=True, nullable=False)
	name: Mapped[str] = mapped_column(db.String, nullable=False)

	#sales: Mapped[list["Sale"]] = relationship(back_populates="Product")

	def __repr__(self) -> str:
		return f"Product(id={self.id!r}, name={self.name!r}"

class Sale(Base):
		__tablename__ = "sales"

		id: Mapped[uuid.UUID] = mapped_column(db.Uuid, primary_key=True, nullable=False)
		product_id = mapped_column(db.ForeignKey("products.id"))
		quantity: Mapped[int] = mapped_column(db.Integer, nullable=False)
		total_value: Mapped[int] = mapped_column(db.Integer, nullable=False)
		date: Mapped[datetime] = mapped_column(db.DateTime, nullable=False)

		#product: Mapped[Product] = relationship(back_populates="Sale")

Base.metadata.create_all(engine)

#newProduct = Product(id = uuid.uuid4(), name = "firstProduct")

#with Session(engine) as session:
#	session.add(newProduct)
#	session.commit()