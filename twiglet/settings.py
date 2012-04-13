# Django settings for twiglet project.
import os

DEBUG = False
TEMPLATE_DEBUG = DEBUG

TARGET = 'prod'
SITE_ROOT= os.getcwd()

EMAIL_USE_TLS = True
EMAIL_HOST = 'smtp.gmail.com'
EMAIL_HOST_USER = 'cs2jcontact@twigletsoftware.com'
EMAIL_HOST_PASSWORD = 'm3vK~+TR'
EMAIL_PORT = 587


ADMINS = (
    ('Kevin Glynn', 'admin@twigletsoftware.com'),
)

MANAGERS = ADMINS

SEND_BROKEN_LINK_EMAILS = True

SERVER_EMAIL = "website.error@twigletsoftware.com"

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql', # Add 'postgresql_psycopg2', 'postgresql', 'mysql', 'sqlite3' or 'oracle'.
        'NAME': TARGET + '_django_twiglet',                      # Or path to database file if using sqlite3.
        'USER': 'twigletsoftwarec',                      # Not used with sqlite3.
        'PASSWORD': '^nDdT6Rt',                  # Not used with sqlite3.
        'HOST': 'mysql.twigletsoftware.com',                      # Set to empty string for localhost. Not used with sqlite3.
        'PORT': '',                      # Set to empty string for default. Not used with sqlite3.
    }
}

# Local time zone for this installation. Choices can be found here:
# http://en.wikipedia.org/wiki/List_of_tz_zones_by_name
# although not all choices may be available on all operating systems.
# On Unix systems, a value of None will cause Django to use the same
# timezone as the operating system.
# If running in a Windows environment this must be set to the same as your
# system time zone.
TIME_ZONE = 'Europe/Brussels'

# Language code for this installation. All choices can be found here:
# http://www.i18nguy.com/unicode/language-identifiers.html
LANGUAGE_CODE = 'en-us'

SITE_ID = 1

# If you set this to False, Django will make some optimizations so as not
# to load the internationalization machinery.
USE_I18N = True

# If you set this to False, Django will not format dates, numbers and
# calendars according to the current locale
USE_L10N = True

# Absolute path to the directory that holds media.
# Example: "/home/media/media.lawrence.com/"
MEDIA_ROOT = os.path.join(SITE_ROOT, 'public/media')

# URL that handles the media served from MEDIA_ROOT. Make sure to use a
# trailing slash if there is a path component (optional in other cases).
# Examples: "http://media.lawrence.com", "http://example.com/media/"
MEDIA_URL = '/media/'

# URL prefix for admin media -- CSS, JavaScript and images. Make sure to use a
# trailing slash.
# Examples: "http://foo.com/media/", "/media/".
ADMIN_MEDIA_PREFIX = '/media/'

# Make this unique, and don't share it with anybody.
SECRET_KEY = '4xkgl!ec3a_!f5u29am9*bpuy%+r+8t2u=s(5azy4f2q!j*^h1'

# List of callables that know how to import templates from various sources.
TEMPLATE_LOADERS = (
    'django.template.loaders.filesystem.Loader',
    'django.template.loaders.app_directories.Loader',
#     'django.template.loaders.eggs.Loader',
)

MIDDLEWARE_CLASSES = (
    'django.middleware.common.CommonMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
)

ROOT_URLCONF = 'twiglet.urls'

TEMPLATE_DIRS = (
    # Put strings here, like "/home/html/django_templates" or "C:/www/django/templates".
    # Always use forward slashes, even on Windows.
    # Don't forget to use absolute paths, not relative paths.
    os.path.join(SITE_ROOT, "twiglet/templates"),
)

INSTALLED_APPS = (
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.sites',
    'django.contrib.messages',
    # Uncomment the next line to enable the admin:
    # 'django.contrib.admin',
    'webapp',
)

## twiglet settings
TWIGLET_ASSETS_DIRS = (
    os.path.join(SITE_ROOT, "twiglet/assets")
    )

TWIGLET_TRIAL_DIR = os.path.join(SITE_ROOT, "assets/cs2j/trial")
TWIGLET_SHOWCASE_DIR = os.path.join(SITE_ROOT, "assets/cs2j/showcase")

