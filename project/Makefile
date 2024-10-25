.PHONY: libsysinfo/libsysinfo.so

MODULES=sysInfo cpuInfo pciInfo usbInfo
HEADERS=$(MODULES:=.h)

MYPROJECT=CS4421GroupProject

all: $(HEADERS) $(MYPROJECT).class libsysinfo/libsysinfo.so

$(MYPROJECT).class: $(MYPROJECT).java libsysinfo/libsysinfo.so
	javac $<

$(HEADERS): %.h: %.java
	javac -h . $<

libsysinfo/libsysinfo.so:
	$(MAKE) -C libsysinfo

