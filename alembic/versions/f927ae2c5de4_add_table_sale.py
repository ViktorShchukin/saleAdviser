"""add table sale

Revision ID: f927ae2c5de4
Revises: d3640517a725
Create Date: 2023-05-31 13:58:18.087413

"""
from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision = 'f927ae2c5de4'
down_revision = 'd3640517a725'
branch_labels = None
depends_on = None


def upgrade() -> None:
    op.create_table(
        "sale",
        sa.Column("id", sa.Uuid, primary_key=True, nullable=False),
        sa.Column("product_id", sa.Uuid, nullable=False), 
        sa.Column("quantity", sa.Integer, nullable=False),
        sa.Column("total_value", sa.Integer, nullable=False),
        sa.Column("date", sa.DateTime, nullable=False),

        sa.PrimaryKeyConstraint("id", name = "sale_pk"),
        sa.ForeignKeyConstraint(["product_id"],["product.id"], name = "realization_product_fk"),
        sa.UniqueConstraint("product_id", "date", name = "sale_product_id_sale_month_unique"),
    )


def downgrade() -> None:
    op.drop_table('sale')
