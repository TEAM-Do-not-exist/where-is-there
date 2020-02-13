from django.urls import path
from . import views

urlpatterns = [
    path('', views.root),
    path('instagram/', views.instagram),
    path('mango/', views.mango),
]
