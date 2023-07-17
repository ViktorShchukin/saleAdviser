import uuid
from datetime import datetime

import sqlalchemy as sa
from sqlalchemy.orm import DeclarativeBase
from sqlalchemy.orm import Mapped
from sqlalchemy.orm import mapped_column
from sqlalchemy.orm import relationship
#from sqlalchemy.orm import Session


#engine = sa.create_engine("sqlite+pysqlite:///database/main.db")

class Base(DeclarativeBase):
	pass

class Product(Base):
	__tablename__ = "product"

	id: Mapped[uuid.UUID] = mapped_column(sa.Uuid, primary_key=True, nullable=False)
	product_name: Mapped[str] = mapped_column(sa.String, nullable=False)

	#sales: Mapped[list["Sale"]] = relationship(back_populates="Product")

	def __repr__(self) -> str:
		return f"Product(id={self.id!r}, product_name={self.product_name!r})"

class Sale(Base):
		__tablename__ = "sale"

		id: Mapped[uuid.UUID] = mapped_column(sa.Uuid, primary_key=True, nullable=False)
		product_id = mapped_column(sa.ForeignKey("product.id"), nullable=False)
		quantity: Mapped[int] = mapped_column(sa.Integer, nullable=False)
		total_value: Mapped[int] = mapped_column(sa.Integer, nullable=False)
		date: Mapped[datetime] = mapped_column(sa.DateTime, nullable=False)

		#product: Mapped[Product] = relationship(back_populates="Sale")

#Base.metadata.create_all(engine)

#newProduct = Product(id = uuid.uuid4(), product_name = "firstProduct")

#with Session(engine) as session:
	#session.add(newProduct)
	#session.commit()