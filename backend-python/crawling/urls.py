from django.urls import path
from . import views

urlpatterns = [
    path('', views.root),
    path('instagram/', views.instagram),
    path('tour-api/', views.tour_api),
]
