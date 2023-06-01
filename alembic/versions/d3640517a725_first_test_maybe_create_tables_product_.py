"""first test. maybe create tables product and sale

Revision ID: d3640517a725
Revises: 
Create Date: 2023-05-25 16:00:24.231877

"""
from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision = 'd3640517a725'
down_revision = None
branch_labels = None
depends_on = None


def upgrade() -> None:
    op.create_table(
        "product",
        sa.Column("id", sa.Uuid, nullable=False),
        sa.Column("product_name", sa.String, nullable=False),

        sa.PrimaryKeyConstraint("id", name = "product_pk"),
    )
    


def downgrade() -> None:
    op.drop_table('product')
