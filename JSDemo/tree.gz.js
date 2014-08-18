window.undefined = window.undefined;
Ext = {
	version : "3.0"
};
Ext.apply = function(d, e, b) {
	if (b) {
		Ext.apply(d, b)
	}
	if (d && e && typeof e == "object") {
		for ( var a in e) {
			d[a] = e[a]
		}
	}
	return d
};
(function() {
	var g = 0, t = Object.prototype.toString, s = function(e) {
		if (Ext.isArray(e) || e.callee) {
			return true
		}
		if (/NodeList|HTMLCollection/.test(t.call(e))) {
			return true
		}
		return ((e.nextNode || e.item) && Ext.isNumber(e.length))
	}, u = navigator.userAgent.toLowerCase(), z = function(e) {
		return e.test(u)
	}, i = document, l = i.compatMode == "CSS1Compat", B = z(/opera/), h = z(/chrome/), v = z(/webkit/), y = !h
			&& z(/safari/), f = y && z(/applewebkit\/4/), b = y && z(/version\/3/), C = y && z(/version\/4/), r = !B
			&& z(/msie/), p = r && z(/msie 7/), o = r && z(/msie 8/), q = r && !p && !o, n = !v && z(/gecko/), d = n
			&& z(/rv:1\.8/), a = n && z(/rv:1\.9/), w = r && !l, A = z(/windows|win32/), k = z(/macintosh|mac os x/), j = z(/adobeair/), m = z(/linux/), c = /^https/i
			.test(window.location.protocol);
	if (q) {
		try {
			i.execCommand("BackgroundImageCache", false, true)
		} catch (x) {
		}
	}
	Ext.apply(Ext, {
		SSL_SECURE_URL : "javascript:false",
		isStrict : l,
		isSecure : c,
		isReady : false,
		enableGarbageCollector : true,
		enableListenerCollection : false,
		USE_NATIVE_JSON : false,
		applyIf : function(D, E) {
			if (D) {
				for ( var e in E) {
					if (Ext.isEmpty(D[e])) {
						D[e] = E[e]
					}
				}
			}
			return D
		},
		id : function(e, D) {
			return (e = Ext.getDom(e) || {}).id = e.id || (D || "ext-gen") + (++g)
		},
		extend : function() {
			var D = function(F) {
				for ( var E in F) {
					this[E] = F[E]
				}
			};
			var e = Object.prototype.constructor;
			return function(K, H, J) {
				if (Ext.isObject(H)) {
					J = H;
					H = K;
					K = J.constructor != e ? J.constructor : function() {
						H.apply(this, arguments)
					}
				}
				var G = function() {
				}, I, E = H.prototype;
				G.prototype = E;
				I = K.prototype = new G();
				I.constructor = K;
				K.superclass = E;
				if (E.constructor == e) {
					E.constructor = H
				}
				K.override = function(F) {
					Ext.override(K, F)
				};
				I.superclass = I.supr = (function() {
					return E
				});
				I.override = D;
				Ext.override(K, J);
				K.extend = function(F) {
					Ext.extend(K, F)
				};
				return K
			}
		}(),
		override : function(e, E) {
			if (E) {
				var D = e.prototype;
				Ext.apply(D, E);
				if (Ext.isIE && E.toString != e.toString) {
					D.toString = E.toString
				}
			}
		},
		namespace : function() {
			var D, e;
			Ext.each(arguments, function(E) {
				e = E.split(".");
				D = window[e[0]] = window[e[0]] || {};
				Ext.each(e.slice(1), function(F) {
					D = D[F] = D[F] || {}
				})
			});
			return D
		},
		urlEncode : function(I, H) {
			var F, D = [], E, G = encodeURIComponent;
			for (E in I) {
				F = !Ext.isDefined(I[E]);
				Ext.each(F ? E : I[E], function(J, e) {
					D.push("&", G(E), "=", (J != E || !F) ? G(J) : "")
				})
			}
			if (!H) {
				D.shift();
				H = ""
			}
			return H + D.join("")
		},
		urlDecode : function(E, D) {
			var H = {}, G = E.split("&"), I = decodeURIComponent, e, F;
			Ext.each(G, function(J) {
				J = J.split("=");
				e = I(J[0]);
				F = I(J[1]);
				H[e] = D || !H[e] ? F : [].concat(H[e]).concat(F)
			});
			return H
		},
		urlAppend : function(e, D) {
			if (!Ext.isEmpty(D)) {
				return e + (e.indexOf("?") === -1 ? "?" : "&") + D
			}
			return e
		},
		toArray : function() {
			return r ? function(e, F, D, E) {
				E = [];
				Ext.each(e, function(G) {
					E.push(G)
				});
				return E.slice(F || 0, D || E.length)
			} : function(e, E, D) {
				return Array.prototype.slice.call(e, E || 0, D || e.length)
			}
		}(),
		each : function(G, F, E) {
			if (Ext.isEmpty(G, true)) {
				return
			}
			if (!s(G) || Ext.isPrimitive(G)) {
				G = [ G ]
			}
			for ( var D = 0, e = G.length; D < e; D++) {
				if (F.call(E || G[D], G[D], D, G) === false) {
					return D
				}
			}
		},
		iterate : function(E, D, e) {
			if (s(E)) {
				Ext.each(E, D, e);
				return
			} else {
				if (Ext.isObject(E)) {
					for ( var F in E) {
						if (E.hasOwnProperty(F)) {
							if (D.call(e || E, F, E[F]) === false) {
								return
							}
						}
					}
				}
			}
		},
		getDom : function(e) {
			if (!e || !i) {
				return null
			}
			return e.dom ? e.dom : (Ext.isString(e) ? i.getElementById(e) : e)
		},
		getBody : function() {
			return Ext.get(i.body || i.documentElement)
		},
		removeNode : r ? function() {
			var e;
			return function(D) {
				if (D && D.tagName != "BODY") {
					e = e || i.createElement("div");
					e.appendChild(D);
					e.innerHTML = ""
				}
			}
		}() : function(e) {
			if (e && e.parentNode && e.tagName != "BODY") {
				e.parentNode.removeChild(e)
			}
		},
		isEmpty : function(D, e) {
			return D === null || D === undefined || ((Ext.isArray(D) && !D.length)) || (!e ? D === "" : false)
		},
		isArray : function(e) {
			return t.apply(e) === "[object Array]"
		},
		isObject : function(e) {
			return e && typeof e == "object"
		},
		isPrimitive : function(e) {
			return Ext.isString(e) || Ext.isNumber(e) || Ext.isBoolean(e)
		},
		isFunction : function(e) {
			return t.apply(e) === "[object Function]"
		},
		isNumber : function(e) {
			return typeof e === "number" && isFinite(e)
		},
		isString : function(e) {
			return typeof e === "string"
		},
		isBoolean : function(e) {
			return typeof e === "boolean"
		},
		isDefined : function(e) {
			return typeof e !== "undefined"
		},
		isOpera : B,
		isWebKit : v,
		isChrome : h,
		isSafari : y,
		isSafari3 : b,
		isSafari4 : C,
		isSafari2 : f,
		isIE : r,
		isIE6 : q,
		isIE7 : p,
		isIE8 : o,
		isGecko : n,
		isGecko2 : d,
		isGecko3 : a,
		isBorderBox : w,
		isLinux : m,
		isWindows : A,
		isMac : k,
		isAir : j
	});
	Ext.ns = Ext.namespace
})();
Ext.ns("Ext", "Ext.util", "Ext.lib", "Ext.data");
Ext.apply(Function.prototype, {
	createInterceptor : function(b, a) {
		var c = this;
		return !Ext.isFunction(b) ? this : function() {
			var e = this, d = arguments;
			b.target = e;
			b.method = c;
			return (b.apply(a || e || window, d) !== false) ? c.apply(e || window, d) : null
		}
	},
	createCallback : function() {
		var a = arguments, b = this;
		return function() {
			return b.apply(window, a)
		}
	},
	createDelegate : function(c, b, a) {
		var d = this;
		return function() {
			var f = b || arguments;
			if (a === true) {
				f = Array.prototype.slice.call(arguments, 0);
				f = f.concat(b)
			} else {
				if (Ext.isNumber(a)) {
					f = Array.prototype.slice.call(arguments, 0);
					var e = [ a, 0 ].concat(b);
					Array.prototype.splice.apply(f, e)
				}
			}
			return d.apply(c || window, f)
		}
	},
	defer : function(c, e, b, a) {
		var d = this.createDelegate(e, b, a);
		if (c > 0) {
			return setTimeout(d, c)
		}
		d();
		return 0
	}
});
Ext.applyIf(String, {
	format : function(b) {
		var a = Ext.toArray(arguments, 1);
		return b.replace(/\{(\d+)\}/g, function(c, d) {
			return a[d]
		})
	}
});
Ext.applyIf(Array.prototype, {
	indexOf : function(c) {
		for ( var b = 0, a = this.length; b < a; b++) {
			if (this[b] == c) {
				return b
			}
		}
		return -1
	},
	remove : function(b) {
		var a = this.indexOf(b);
		if (a != -1) {
			this.splice(a, 1)
		}
		return this
	}
});
Ext.ns("Ext.grid", "Ext.dd", "Ext.tree", "Ext.form", "Ext.menu", "Ext.state", "Ext.layout", "Ext.app", "Ext.ux",
		"Ext.chart", "Ext.direct");
Ext.apply(Ext, function() {
	var b = Ext, a = 0;
	return {
		emptyFn : function() {
		},
		BLANK_IMAGE_URL : Ext.isIE6 || Ext.isIE7 ? "http://extjs.com/s.gif"
				: "data:image/gif;base64,R0lGODlhAQABAID/AMDAwAAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==",
		extendX : function(c, d) {
			return Ext.extend(c, d(c.prototype))
		},
		getDoc : function() {
			return Ext.get(document)
		},
		isDate : function(c) {
			return Object.prototype.toString.apply(c) === "[object Date]"
		},
		num : function(d, c) {
			d = Number(d === null || typeof d == "boolean" ? NaN : d);
			return isNaN(d) ? c : d
		},
		value : function(e, c, d) {
			return Ext.isEmpty(e, d) ? c : e
		},
		escapeRe : function(c) {
			return c.replace(/([.*+?^${}()|[\]\/\\])/g, "\\$1")
		},
		sequence : function(f, c, e, d) {
			f[c] = f[c].createSequence(e, d)
		},
		addBehaviors : function(g) {
			if (!Ext.isReady) {
				Ext.onReady(function() {
					Ext.addBehaviors(g)
				})
			} else {
				var d = {}, f, c, e;
				for (c in g) {
					if ((f = c.split("@"))[1]) {
						e = f[0];
						if (!d[e]) {
							d[e] = Ext.select(e)
						}
						d[e].on(f[1], g[c])
					}
				}
				d = null
			}
		},
		combine : function() {
			var e = arguments, d = e.length, g = [];
			for ( var f = 0; f < d; f++) {
				var c = e[f];
				if (Ext.isArray(c)) {
					g = g.concat(c)
				} else {
					if (c.length !== undefined && !c.substr) {
						g = g.concat(Array.prototype.slice.call(c, 0))
					} else {
						g.push(c)
					}
				}
			}
			return g
		},
		copyTo : function(c, d, e) {
			if (typeof e == "string") {
				e = e.split(/[,;\s]/)
			}
			Ext.each(e, function(f) {
				if (d.hasOwnProperty(f)) {
					c[f] = d[f]
				}
			}, this);
			return c
		},
		destroy : function() {
			Ext.each(arguments, function(c) {
				if (c) {
					if (Ext.isArray(c)) {
						this.destroy.apply(this, c)
					} else {
						if (Ext.isFunction(c.destroy)) {
							c.destroy()
						} else {
							if (c.dom) {
								c.remove()
							}
						}
					}
				}
			}, this)
		},
		destroyMembers : function(j, g, e, f) {
			for ( var h = 1, d = arguments, c = d.length; h < c; h++) {
				Ext.destroy(j[d[h]]);
				delete j[d[h]]
			}
		},
		clean : function(c) {
			var d = [];
			Ext.each(c, function(e) {
				if (!!e) {
					d.push(e)
				}
			});
			return d
		},
		unique : function(c) {
			var d = [], e = {};
			Ext.each(c, function(f) {
				if (!e[f]) {
					d.push(f)
				}
				e[f] = true
			});
			return d
		},
		flatten : function(c) {
			var e = [];
			function d(f) {
				Ext.each(f, function(g) {
					if (Ext.isArray(g)) {
						d(g)
					} else {
						e.push(g)
					}
				});
				return e
			}
			return d(c)
		},
		min : function(c, d) {
			var e = c[0];
			d = d || function(g, f) {
				return g < f ? -1 : 1
			};
			Ext.each(c, function(f) {
				e = d(e, f) == -1 ? e : f
			});
			return e
		},
		max : function(c, d) {
			var e = c[0];
			d = d || function(g, f) {
				return g > f ? 1 : -1
			};
			Ext.each(c, function(f) {
				e = d(e, f) == 1 ? e : f
			});
			return e
		},
		mean : function(c) {
			return Ext.sum(c) / c.length
		},
		sum : function(c) {
			var d = 0;
			Ext.each(c, function(e) {
				d += e
			});
			return d
		},
		partition : function(c, d) {
			var e = [ [], [] ];
			Ext.each(c, function(g, h, f) {
				e[(d && d(g, h, f)) || (!d && g) ? 0 : 1].push(g)
			});
			return e
		},
		invoke : function(c, d) {
			var f = [], e = Array.prototype.slice.call(arguments, 2);
			Ext.each(c, function(g, h) {
				if (g && typeof g[d] == "function") {
					f.push(g[d].apply(g, e))
				} else {
					f.push(undefined)
				}
			});
			return f
		},
		pluck : function(c, e) {
			var d = [];
			Ext.each(c, function(f) {
				d.push(f[e])
			});
			return d
		},
		zip : function() {
			var l = Ext.partition(arguments, function(i) {
				return !Ext.isFunction(i)
			}), g = l[0], k = l[1][0], c = Ext.max(Ext.pluck(g, "length")), f = [];
			for ( var h = 0; h < c; h++) {
				f[h] = [];
				if (k) {
					f[h] = k.apply(k, Ext.pluck(g, h))
				} else {
					for ( var e = 0, d = g.length; e < d; e++) {
						f[h].push(g[e][h])
					}
				}
			}
			return f
		},
		getCmp : function(c) {
			return Ext.ComponentMgr.get(c)
		},
		useShims : b.isIE6 || (b.isMac && b.isGecko2),
		type : function(d) {
			if (d === undefined || d === null) {
				return false
			}
			if (d.htmlElement) {
				return "element"
			}
			var c = typeof d;
			if (c == "object" && d.nodeName) {
				switch (d.nodeType) {
				case 1:
					return "element";
				case 3:
					return (/\S/).test(d.nodeValue) ? "textnode" : "whitespace"
				}
			}
			if (c == "object" || c == "function") {
				switch (d.constructor) {
				case Array:
					return "array";
				case RegExp:
					return "regexp";
				case Date:
					return "date"
				}
				if (typeof d.length == "number" && typeof d.item == "function") {
					return "nodelist"
				}
			}
			return c
		},
		intercept : function(f, c, e, d) {
			f[c] = f[c].createInterceptor(e, d)
		},
		callback : function(c, f, e, d) {
			if (Ext.isFunction(c)) {
				if (d) {
					c.defer(d, f, e || [])
				} else {
					c.apply(f, e || [])
				}
			}
		}
	}
}());
Ext.apply(Function.prototype, {
	createSequence : function(b, a) {
		var c = this;
		return !Ext.isFunction(b) ? this : function() {
			var d = c.apply(this || window, arguments);
			b.apply(a || this || window, arguments);
			return d
		}
	}
});
Ext.applyIf(String, {
	escape : function(a) {
		return a.replace(/('|\\)/g, "\\$1")
	},
	leftPad : function(d, b, c) {
		var a = String(d);
		if (!c) {
			c = " "
		}
		while (a.length < b) {
			a = c + a
		}
		return a
	}
});
String.prototype.toggle = function(b, a) {
	return this == b ? a : b
};
String.prototype.trim = function() {
	var a = /^\s+|\s+$/g;
	return function() {
		return this.replace(a, "")
	}
}();
Date.prototype.getElapsed = function(a) {
	return Math.abs((a || new Date()).getTime() - this.getTime())
};
Ext.applyIf(Number.prototype, {
	constrain : function(b, a) {
		return Math.min(Math.max(this, b), a)
	}
});
Ext.util.TaskRunner = function(e) {
	e = e || 10;
	var f = [], a = [], b = 0, g = false, d = function() {
		g = false;
		clearInterval(b);
		b = 0
	}, h = function() {
		if (!g) {
			g = true;
			b = setInterval(i, e)
		}
	}, c = function(j) {
		a.push(j);
		if (j.onStop) {
			j.onStop.apply(j.scope || j)
		}
	}, i = function() {
		var l = a.length, n = new Date().getTime();
		if (l > 0) {
			for ( var p = 0; p < l; p++) {
				f.remove(a[p])
			}
			a = [];
			if (f.length < 1) {
				d();
				return
			}
		}
		for ( var p = 0, o, k, m, j = f.length; p < j; ++p) {
			o = f[p];
			k = n - o.taskRunTime;
			if (o.interval <= k) {
				m = o.run.apply(o.scope || o, o.args || [ ++o.taskRunCount ]);
				o.taskRunTime = n;
				if (m === false || o.taskRunCount === o.repeat) {
					c(o);
					return
				}
			}
			if (o.duration && o.duration <= (n - o.taskStartTime)) {
				c(o)
			}
		}
	};
	this.start = function(j) {
		f.push(j);
		j.taskStartTime = new Date().getTime();
		j.taskRunTime = 0;
		j.taskRunCount = 0;
		h();
		return j
	};
	this.stop = function(j) {
		c(j);
		return j
	};
	this.stopAll = function() {
		d();
		for ( var k = 0, j = f.length; k < j; k++) {
			if (f[k].onStop) {
				f[k].onStop()
			}
		}
		f = [];
		a = []
	}
};
Ext.TaskMgr = new Ext.util.TaskRunner();
(function() {
	var b;
	function c(d) {
		if (!b) {
			b = new Ext.Element.Flyweight()
		}
		b.dom = d;
		return b
	}
	(function() {
		var f = document, d = f.compatMode == "CSS1Compat", e = Math.max, g = parseInt;
		Ext.lib.Dom = {
			isAncestor : function(i, j) {
				var h = false;
				i = Ext.getDom(i);
				j = Ext.getDom(j);
				if (i && j) {
					if (i.contains) {
						return i.contains(j)
					} else {
						if (i.compareDocumentPosition) {
							return !!(i.compareDocumentPosition(j) & 16)
						} else {
							while (j = j.parentNode) {
								h = j == i || h
							}
						}
					}
				}
				return h
			},
			getViewWidth : function(h) {
				return h ? this.getDocumentWidth() : this.getViewportWidth()
			},
			getViewHeight : function(h) {
				return h ? this.getDocumentHeight() : this.getViewportHeight()
			},
			getDocumentHeight : function() {
				return e(!d ? f.body.scrollHeight : f.documentElement.scrollHeight, this.getViewportHeight())
			},
			getDocumentWidth : function() {
				return e(!d ? f.body.scrollWidth : f.documentElement.scrollWidth, this.getViewportWidth())
			},
			getViewportHeight : function() {
				return Ext.isIE ? (Ext.isStrict ? f.documentElement.clientHeight : f.body.clientHeight)
						: self.innerHeight
			},
			getViewportWidth : function() {
				return !Ext.isStrict && !Ext.isOpera ? f.body.clientWidth : Ext.isIE ? f.documentElement.clientWidth
						: self.innerWidth
			},
			getY : function(h) {
				return this.getXY(h)[1]
			},
			getX : function(h) {
				return this.getXY(h)[0]
			},
			getXY : function(j) {
				var i, o, r, u, k, l, t = 0, q = 0, s, h, m = (f.body || f.documentElement), n = [ 0, 0 ];
				j = Ext.getDom(j);
				if (j != m) {
					if (j.getBoundingClientRect) {
						r = j.getBoundingClientRect();
						s = c(document).getScroll();
						n = [ r.left + s.left, r.top + s.top ]
					} else {
						i = j;
						h = c(j).isStyle("position", "absolute");
						while (i) {
							o = c(i);
							t += i.offsetLeft;
							q += i.offsetTop;
							h = h || o.isStyle("position", "absolute");
							if (Ext.isGecko) {
								q += u = g(o.getStyle("borderTopWidth"), 10) || 0;
								t += k = g(o.getStyle("borderLeftWidth"), 10) || 0;
								if (i != j && !o.isStyle("overflow", "visible")) {
									t += k;
									q += u
								}
							}
							i = i.offsetParent
						}
						if (Ext.isSafari && h) {
							t -= m.offsetLeft;
							q -= m.offsetTop
						}
						if (Ext.isGecko && !h) {
							l = c(m);
							t += g(l.getStyle("borderLeftWidth"), 10) || 0;
							q += g(l.getStyle("borderTopWidth"), 10) || 0
						}
						i = j.parentNode;
						while (i && i != m) {
							if (!Ext.isOpera || (i.tagName != "TR" && !c(i).isStyle("display", "inline"))) {
								t -= i.scrollLeft;
								q -= i.scrollTop
							}
							i = i.parentNode
						}
						n = [ t, q ]
					}
				}
				return n
			},
			setXY : function(i, j) {
				(i = Ext.fly(i, "_setXY")).position();
				var k = i.translatePoints(j), h = i.dom.style, l;
				for (l in k) {
					if (!isNaN(k[l])) {
						h[l] = k[l] + "px"
					}
				}
			},
			setX : function(i, h) {
				this.setXY(i, [ h, false ])
			},
			setY : function(h, i) {
				this.setXY(h, [ false, i ])
			}
		}
	})();
	Ext.lib.Dom.getRegion = function(d) {
		return Ext.lib.Region.getRegion(d)
	};
	Ext.lib.Event = function() {
		var y = false, w = [], g = [], D = 0, q = [], d, G = false, k = window, K = document, l = 200, t = 20, E = 0, s = 1, i = 2, m = 3, u = 3, z = 4, v = "scrollLeft", r = "scrollTop", f = "unload", B = "mouseover", J = "mouseout", e = function() {
			var L;
			if (k.addEventListener) {
				L = function(P, N, O, M) {
					if (N == "mouseenter") {
						O = O.createInterceptor(o);
						P.addEventListener(B, O, (M))
					} else {
						if (N == "mouseleave") {
							O = O.createInterceptor(o);
							P.addEventListener(J, O, (M))
						} else {
							P.addEventListener(N, O, (M))
						}
					}
					return O
				}
			} else {
				if (k.attachEvent) {
					L = function(P, N, O, M) {
						P.attachEvent("on" + N, O);
						return O
					}
				} else {
					L = function() {
					}
				}
			}
			return L
		}(), h = function() {
			var L;
			if (k.removeEventListener) {
				L = function(P, N, O, M) {
					if (N == "mouseenter") {
						N = B
					} else {
						if (N == "mouseleave") {
							N = J
						}
					}
					P.removeEventListener(N, O, (M))
				}
			} else {
				if (k.detachEvent) {
					L = function(O, M, N) {
						O.detachEvent("on" + M, N)
					}
				} else {
					L = function() {
					}
				}
			}
			return L
		}();
		var F = Ext.isGecko ? function(L) {
			return Object.prototype.toString.call(L) == "[object XULElement]"
		} : function() {
		};
		var p = Ext.isGecko ? function(L) {
			try {
				return L.nodeType == 3
			} catch (M) {
				return false
			}
		} : function(L) {
			return L.nodeType == 3
		};
		function o(M) {
			var L = A.getRelatedTarget(M);
			return !(F(L) || x(M.currentTarget, L))
		}
		function x(L, N) {
			if (L && L.firstChild) {
				while (N) {
					if (N === L) {
						return true
					}
					try {
						N = N.parentNode
					} catch (M) {
						return false
					}
					if (N && (N.nodeType != 1)) {
						N = null
					}
				}
			}
			return false
		}
		function C(O, L, N) {
			var M = -1;
			Ext.each(w, function(P, Q) {
				if (P && P[i] == N && P[E] == O && P[s] == L) {
					M = Q
				}
			});
			return M
		}
		function H() {
			var L = false, O = [], M, N = !y || (D > 0);
			if (!G) {
				G = true;
				Ext.each(q, function(Q, R, P) {
					if (Q && (M = K.getElementById(Q.id))) {
						if (!Q.checkReady || y || M.nextSibling || (K && K.body)) {
							M = Q.override ? (Q.override === true ? Q.obj : Q.override) : M;
							Q.fn.call(M, Q.obj);
							q[R] = null
						} else {
							O.push(Q)
						}
					}
				});
				D = (O.length === 0) ? 0 : D - 1;
				if (N) {
					n()
				} else {
					clearInterval(d);
					d = null
				}
				L = !(G = false)
			}
			return L
		}
		function n() {
			if (!d) {
				var L = function() {
					H()
				};
				d = setInterval(L, t)
			}
		}
		function I() {
			var L = K.documentElement, M = K.body;
			if (L && (L[r] || L[v])) {
				return [ L[v], L[r] ]
			} else {
				if (M) {
					return [ M[v], M[r] ]
				} else {
					return [ 0, 0 ]
				}
			}
		}
		function j(L, M) {
			L = L.browserEvent || L;
			var N = L["page" + M];
			if (!N && N !== 0) {
				N = L["client" + M] || 0;
				if (Ext.isIE) {
					N += I()[M == "X" ? 0 : 1]
				}
			}
			return N
		}
		var A = {
			onAvailable : function(N, L, O, M) {
				q.push({
					id : N,
					fn : L,
					obj : O,
					override : M,
					checkReady : false
				});
				D = l;
				n()
			},
			addListener : function(O, L, N) {
				var M;
				O = Ext.getDom(O);
				if (O && N) {
					if (f == L) {
						M = !!(g[g.length] = [ O, L, N ])
					} else {
						w.push([ O, L, N, M = e(O, L, N, false) ])
					}
				}
				return !!M
			},
			removeListener : function(Q, M, P) {
				var O = false, N, L;
				Q = Ext.getDom(Q);
				if (!P) {
					O = this.purgeElement(Q, false, M)
				} else {
					if (f == M) {
						Ext.each(g, function(S, T, R) {
							if (S && S[0] == Q && S[1] == M && S[2] == P) {
								g.splice(T, 1);
								O = true
							}
						})
					} else {
						N = arguments[3] || C(Q, M, P);
						L = w[N];
						if (Q && L) {
							h(Q, M, L[m], false);
							L[m] = L[i] = null;
							w.splice(N, 1);
							O = true
						}
					}
				}
				return O
			},
			getTarget : function(L) {
				L = L.browserEvent || L;
				return this.resolveTextNode(L.target || L.srcElement)
			},
			resolveTextNode : function(L) {
				return L && !F(L) && p(L) ? L.parentNode : L
			},
			getRelatedTarget : function(L) {
				L = L.browserEvent || L;
				return this.resolveTextNode(L.relatedTarget
						|| (L.type == J ? L.toElement : L.type == B ? L.fromElement : null))
			},
			getPageX : function(L) {
				return j(L, "X")
			},
			getPageY : function(L) {
				return j(L, "Y")
			},
			getXY : function(L) {
				return [ this.getPageX(L), this.getPageY(L) ]
			},
			stopEvent : function(L) {
				this.stopPropagation(L);
				this.preventDefault(L)
			},
			stopPropagation : function(L) {
				L = L.browserEvent || L;
				if (L.stopPropagation) {
					L.stopPropagation()
				} else {
					L.cancelBubble = true
				}
			},
			preventDefault : function(L) {
				L = L.browserEvent || L;
				if (L.preventDefault) {
					L.preventDefault()
				} else {
					L.returnValue = false
				}
			},
			getEvent : function(L) {
				L = L || k.event;
				if (!L) {
					var M = this.getEvent.caller;
					while (M) {
						L = M.arguments[0];
						if (L && Event == L.constructor) {
							break
						}
						M = M.caller
					}
				}
				return L
			},
			getCharCode : function(L) {
				L = L.browserEvent || L;
				return L.charCode || L.keyCode || 0
			},
			_load : function(M) {
				y = true;
				var L = Ext.lib.Event;
				if (Ext.isIE && M !== true) {
					h(k, "load", arguments.callee)
				}
			},
			purgeElement : function(M, O, L) {
				var N = this;
				Ext.each(N.getListeners(M, L), function(P) {
					if (P) {
						N.removeListener(M, P.type, P.fn)
					}
				});
				if (O && M && M.childNodes) {
					Ext.each(M.childNodes, function(P) {
						N.purgeElement(P, O, L)
					})
				}
			},
			getListeners : function(O, M) {
				var P = this, N = [], L;
				if (M) {
					L = M == f ? g : w
				} else {
					L = w.concat(g)
				}
				Ext.each(L, function(Q, R) {
					if (Q && Q[E] == O && (!M || M == Q[s])) {
						N.push({
							type : Q[s],
							fn : Q[i],
							obj : Q[u],
							adjust : Q[z],
							index : R
						})
					}
				});
				return N.length ? N : null
			},
			_unload : function(S) {
				var R = Ext.lib.Event, P, O, M, L, N, Q;
				Ext.each(g, function(T) {
					if (T) {
						try {
							Q = T[z] ? (T[z] === true ? T[u] : T[z]) : k;
							T[i].call(Q, R.getEvent(S), T[u])
						} catch (U) {
						}
					}
				});
				g = null;
				if (w && (O = w.length)) {
					while (O) {
						if ((M = w[N = --O])) {
							R.removeListener(M[E], M[s], M[i], N)
						}
					}
				}
				h(k, f, R._unload)
			}
		};
		A.on = A.addListener;
		A.un = A.removeListener;
		if (K && K.body) {
			A._load(true)
		} else {
			e(k, "load", A._load)
		}
		e(k, f, A._unload);
		H();
		return A
	}();
	Ext.lib.Ajax = function() {
		var g = [ "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP" ], d = "Content-Type";
		function h(s) {
			var r = s.conn, t;
			function q(u, v) {
				for (t in v) {
					if (v.hasOwnProperty(t)) {
						u.setRequestHeader(t, v[t])
					}
				}
			}
			if (k.defaultHeaders) {
				q(r, k.defaultHeaders)
			}
			if (k.headers) {
				q(r, k.headers);
				k.headers = null
			}
		}
		function e(t, s, r, q) {
			return {
				tId : t,
				status : r ? -1 : 0,
				statusText : r ? "transaction aborted" : "communication failure",
				isAbort : true,
				isTimeout : true,
				argument : s
			}
		}
		function j(q, r) {
			(k.headers = k.headers || {})[q] = r
		}
		function o(z, x) {
			var r = {}, v, w = z.conn, q, u;
			try {
				v = z.conn.getAllResponseHeaders();
				Ext.each(v.replace(/\r\n/g, "\n").split("\n"), function(s) {
					q = s.indexOf(":");
					if (q >= 0) {
						u = s.substr(0, q).toLowerCase();
						if (s.charAt(q + 1) == " ") {
							++q
						}
						r[u] = s.substr(q + 1)
					}
				})
			} catch (y) {
			}
			return {
				tId : z.tId,
				status : w.status,
				statusText : w.statusText,
				getResponseHeader : function(s) {
					return r[s.toLowerCase()]
				},
				getAllResponseHeaders : function() {
					return v
				},
				responseText : w.responseText,
				responseXML : w.responseXML,
				argument : x
			}
		}
		function n(q) {
			q.conn = null;
			q = null
		}
		function f(v, w, r, q) {
			if (!w) {
				n(v);
				return
			}
			var t, s;
			try {
				if (v.conn.status !== undefined && v.conn.status != 0) {
					t = v.conn.status
				} else {
					t = 13030
				}
			} catch (u) {
				t = 13030
			}
			if ((t >= 200 && t < 300) || (Ext.isIE && t == 1223)) {
				s = o(v, w.argument);
				if (w.success) {
					if (!w.scope) {
						w.success(s)
					} else {
						w.success.apply(w.scope, [ s ])
					}
				}
			} else {
				switch (t) {
				case 12002:
				case 12029:
				case 12030:
				case 12031:
				case 12152:
				case 13030:
					s = e(v.tId, w.argument, (r ? r : false), q);
					if (w.failure) {
						if (!w.scope) {
							w.failure(s)
						} else {
							w.failure.apply(w.scope, [ s ])
						}
					}
					break;
				default:
					s = o(v, w.argument);
					if (w.failure) {
						if (!w.scope) {
							w.failure(s)
						} else {
							w.failure.apply(w.scope, [ s ])
						}
					}
				}
			}
			n(v);
			s = null
		}
		function m(s, v) {
			v = v || {};
			var q = s.conn, u = s.tId, r = k.poll, t = v.timeout || null;
			if (t) {
				k.timeout[u] = setTimeout(function() {
					k.abort(s, v, true)
				}, t)
			}
			r[u] = setInterval(function() {
				if (q && q.readyState == 4) {
					clearInterval(r[u]);
					r[u] = null;
					if (t) {
						clearTimeout(k.timeout[u]);
						k.timeout[u] = null
					}
					f(s, v)
				}
			}, k.pollInterval)
		}
		function i(u, r, t, q) {
			var s = l() || null;
			if (s) {
				s.conn.open(u, r, true);
				if (k.useDefaultXhrHeader) {
					j("X-Requested-With", k.defaultXhrHeader)
				}
				if (q && k.useDefaultHeader && (!k.headers || !k.headers[d])) {
					j(d, k.defaultPostHeader)
				}
				if (k.defaultHeaders || k.headers) {
					h(s)
				}
				m(s, t);
				s.conn.send(q || null)
			}
			return s
		}
		function l() {
			var r;
			try {
				if (r = p(k.transactionId)) {
					k.transactionId++
				}
			} catch (q) {
			} finally {
				return r
			}
		}
		function p(t) {
			var q;
			try {
				q = new XMLHttpRequest()
			} catch (s) {
				for ( var r = 0; r < g.length; ++r) {
					try {
						q = new ActiveXObject(g[r]);
						break
					} catch (s) {
					}
				}
			} finally {
				return {
					conn : q,
					tId : t
				}
			}
		}
		var k = {
			request : function(q, s, t, u, y) {
				if (y) {
					var v = this, r = y.xmlData, w = y.jsonData, x;
					Ext.applyIf(v, y);
					if (r || w) {
						x = v.headers;
						if (!x || !x[d]) {
							j(d, r ? "text/xml" : "application/json")
						}
						u = r || (Ext.isObject(w) ? Ext.encode(w) : w)
					}
				}
				return i(q || y.method || "POST", s, t, u)
			},
			serializeForm : function(r) {
				var s = r.elements || (document.forms[r] || Ext.getDom(r)).elements, y = false, x = encodeURIComponent, v, z, q, t, u = "", w;
				Ext.each(s, function(A) {
					q = A.name;
					w = A.type;
					if (!A.disabled && q) {
						if (/select-(one|multiple)/i.test(w)) {
							Ext.each(A.options, function(B) {
								if (B.selected) {
									u += String.format("{0}={1}&", x(q), (B.hasAttribute ? B.hasAttribute("value") : B
											.getAttributeNode("value").specified) ? B.value : B.text)
								}
							})
						} else {
							if (!/file|undefined|reset|button/i.test(w)) {
								if (!(/radio|checkbox/i.test(w) && !A.checked) && !(w == "submit" && y)) {
									u += x(q) + "=" + x(A.value) + "&";
									y = /submit/i.test(w)
								}
							}
						}
					}
				});
				return u.substr(0, u.length - 1)
			},
			useDefaultHeader : true,
			defaultPostHeader : "application/x-www-form-urlencoded; charset=UTF-8",
			useDefaultXhrHeader : true,
			defaultXhrHeader : "XMLHttpRequest",
			poll : {},
			timeout : {},
			pollInterval : 50,
			transactionId : 0,
			abort : function(t, v, q) {
				var s = this, u = t.tId, r = false;
				if (s.isCallInProgress(t)) {
					t.conn.abort();
					clearInterval(s.poll[u]);
					s.poll[u] = null;
					if (q) {
						s.timeout[u] = null
					}
					f(t, v, (r = true), q)
				}
				return r
			},
			isCallInProgress : function(q) {
				return q.conn && !{
					0 : true,
					4 : true
				}[q.conn.readyState]
			}
		};
		return k
	}();
	Ext.lib.Region = function(f, h, d, e) {
		var g = this;
		g.top = f;
		g[1] = f;
		g.right = h;
		g.bottom = d;
		g.left = e;
		g[0] = e
	};
	Ext.lib.Region.prototype = {
		contains : function(e) {
			var d = this;
			return (e.left >= d.left && e.right <= d.right && e.top >= d.top && e.bottom <= d.bottom)
		},
		getArea : function() {
			var d = this;
			return ((d.bottom - d.top) * (d.right - d.left))
		},
		intersect : function(i) {
			var h = this, f = Math.max(h.top, i.top), g = Math.min(h.right, i.right), d = Math.min(h.bottom, i.bottom), e = Math
					.max(h.left, i.left);
			if (d >= f && g >= e) {
				return new Ext.lib.Region(f, g, d, e)
			}
		},
		union : function(i) {
			var h = this, f = Math.min(h.top, i.top), g = Math.max(h.right, i.right), d = Math.max(h.bottom, i.bottom), e = Math
					.min(h.left, i.left);
			return new Ext.lib.Region(f, g, d, e)
		},
		constrainTo : function(e) {
			var d = this;
			d.top = d.top.constrain(e.top, e.bottom);
			d.bottom = d.bottom.constrain(e.top, e.bottom);
			d.left = d.left.constrain(e.left, e.right);
			d.right = d.right.constrain(e.left, e.right);
			return d
		},
		adjust : function(f, e, d, h) {
			var g = this;
			g.top += f;
			g.left += e;
			g.right += h;
			g.bottom += d;
			return g
		}
	};
	Ext.lib.Region.getRegion = function(g) {
		var i = Ext.lib.Dom.getXY(g), f = i[1], h = i[0] + g.offsetWidth, d = i[1] + g.offsetHeight, e = i[0];
		return new Ext.lib.Region(f, h, d, e)
	};
	Ext.lib.Point = function(d, f) {
		if (Ext.isArray(d)) {
			f = d[1];
			d = d[0]
		}
		var e = this;
		e.x = e.right = e.left = e[0] = d;
		e.y = e.top = e.bottom = e[1] = f
	};
	Ext.lib.Point.prototype = new Ext.lib.Region();
	(function() {
		var g = Ext.lib, i = /width|height|opacity|padding/i, f = /^((width|height)|(top|left))$/, d = /width|height|top$|bottom$|left$|right$/i, h = /\d+(em|%|en|ex|pt|in|cm|mm|pc)$/i, j = function(
				k) {
			return typeof k !== "undefined"
		}, e = function() {
			return new Date()
		};
		g.Anim = {
			motion : function(n, l, o, p, k, m) {
				return this.run(n, l, o, p, k, m, Ext.lib.Motion)
			},
			run : function(o, l, q, r, k, n, m) {
				m = m || Ext.lib.AnimBase;
				if (typeof r == "string") {
					r = Ext.lib.Easing[r]
				}
				var p = new m(o, l, q, r);
				p.animateX(function() {
					if (Ext.isFunction(k)) {
						k.call(n)
					}
				});
				return p
			}
		};
		g.AnimBase = function(l, k, m, n) {
			if (l) {
				this.init(l, k, m, n)
			}
		};
		g.AnimBase.prototype = {
			doMethod : function(k, n, l) {
				var m = this;
				return m.method(m.curFrame, n, l - n, m.totalFrames)
			},
			setAttr : function(k, m, l) {
				if (i.test(k) && m < 0) {
					m = 0
				}
				Ext.fly(this.el, "_anim").setStyle(k, m + l)
			},
			getAttr : function(k) {
				var m = Ext.fly(this.el), n = m.getStyle(k), l = f.exec(k) || [];
				if (n !== "auto" && !h.test(n)) {
					return parseFloat(n)
				}
				return (!!(l[2]) || (m.getStyle("position") == "absolute" && !!(l[3]))) ? m.dom["offset"
						+ l[0].charAt(0).toUpperCase() + l[0].substr(1)] : 0
			},
			getDefaultUnit : function(k) {
				return d.test(k) ? "px" : ""
			},
			animateX : function(n, k) {
				var l = this, m = function() {
					l.onComplete.removeListener(m);
					if (Ext.isFunction(n)) {
						n.call(k || l, l)
					}
				};
				l.onComplete.addListener(m, l);
				l.animate()
			},
			setRunAttr : function(n) {
				var p = this, q = this.attributes[n], r = q.to, o = q.by, s = q.from, t = q.unit, l = (this.runAttrs[n] = {}), m;
				if (!j(r) && !j(o)) {
					return false
				}
				var k = j(s) ? s : p.getAttr(n);
				if (j(r)) {
					m = r
				} else {
					if (j(o)) {
						if (Ext.isArray(k)) {
							m = [];
							Ext.each(k, function(u, w) {
								m[w] = u + o[w]
							})
						} else {
							m = k + o
						}
					}
				}
				Ext.apply(l, {
					start : k,
					end : m,
					unit : j(t) ? t : p.getDefaultUnit(n)
				})
			},
			init : function(l, p, o, k) {
				var r = this, n = 0, s = g.AnimMgr;
				Ext.apply(r, {
					isAnimated : false,
					startTime : null,
					el : Ext.getDom(l),
					attributes : p || {},
					duration : o || 1,
					method : k || g.Easing.easeNone,
					useSec : true,
					curFrame : 0,
					totalFrames : s.fps,
					runAttrs : {},
					animate : function() {
						var u = this, v = u.duration;
						if (u.isAnimated) {
							return false
						}
						u.curFrame = 0;
						u.totalFrames = u.useSec ? Math.ceil(s.fps * v) : v;
						s.registerElement(u)
					},
					stop : function(u) {
						var v = this;
						if (u) {
							v.curFrame = v.totalFrames;
							v._onTween.fire()
						}
						s.stop(v)
					}
				});
				var t = function() {
					var v = this, u;
					v.onStart.fire();
					v.runAttrs = {};
					for (u in this.attributes) {
						this.setRunAttr(u)
					}
					v.isAnimated = true;
					v.startTime = e();
					n = 0
				};
				var q = function() {
					var v = this;
					v.onTween.fire({
						duration : e() - v.startTime,
						curFrame : v.curFrame
					});
					var w = v.runAttrs;
					for ( var u in w) {
						this.setAttr(u, v.doMethod(u, w[u].start, w[u].end), w[u].unit)
					}
					++n
				};
				var m = function() {
					var u = this, w = (e() - u.startTime) / 1000, v = {
						duration : w,
						frames : n,
						fps : n / w
					};
					u.isAnimated = false;
					n = 0;
					u.onComplete.fire(v)
				};
				r.onStart = new Ext.util.Event(r);
				r.onTween = new Ext.util.Event(r);
				r.onComplete = new Ext.util.Event(r);
				(r._onStart = new Ext.util.Event(r)).addListener(t);
				(r._onTween = new Ext.util.Event(r)).addListener(q);
				(r._onComplete = new Ext.util.Event(r)).addListener(m)
			}
		};
		Ext.lib.AnimMgr = new function() {
			var o = this, m = null, l = [], k = 0;
			Ext.apply(o, {
				fps : 1000,
				delay : 1,
				registerElement : function(q) {
					l.push(q);
					++k;
					q._onStart.fire();
					o.start()
				},
				unRegister : function(r, q) {
					r._onComplete.fire();
					q = q || p(r);
					if (q != -1) {
						l.splice(q, 1)
					}
					if (--k <= 0) {
						o.stop()
					}
				},
				start : function() {
					if (m === null) {
						m = setInterval(o.run, o.delay)
					}
				},
				stop : function(s) {
					if (!s) {
						clearInterval(m);
						for ( var r = 0, q = l.length; r < q; ++r) {
							if (l[0].isAnimated) {
								o.unRegister(l[0], 0)
							}
						}
						l = [];
						m = null;
						k = 0
					} else {
						o.unRegister(s)
					}
				},
				run : function() {
					var q;
					Ext.each(l, function(r) {
						if (r && r.isAnimated) {
							q = r.totalFrames;
							if (r.curFrame < q || q === null) {
								++r.curFrame;
								if (r.useSec) {
									n(r)
								}
								r._onTween.fire()
							} else {
								o.stop(r)
							}
						}
					}, o)
				}
			});
			var p = function(r) {
				var q = -1;
				Ext.each(l, function(t, s) {
					if (t == r) {
						q = s;
						return false
					}
				});
				return q
			};
			var n = function(r) {
				var v = r.totalFrames, u = r.curFrame, t = r.duration, s = (u * t * 1000 / v), q = (e() - r.startTime), w = 0;
				if (q < t * 1000) {
					w = Math.round((q / s - 1) * u)
				} else {
					w = v - (u + 1)
				}
				if (w > 0 && isFinite(w)) {
					if (r.curFrame + w >= v) {
						w = v - (u + 1)
					}
					r.curFrame += w
				}
			}
		};
		g.Bezier = new function() {
			this.getPosition = function(p, o) {
				var r = p.length, m = [], q = 1 - o, l, k;
				for (l = 0; l < r; ++l) {
					m[l] = [ p[l][0], p[l][1] ]
				}
				for (k = 1; k < r; ++k) {
					for (l = 0; l < r - k; ++l) {
						m[l][0] = q * m[l][0] + o * m[parseInt(l + 1, 10)][0];
						m[l][1] = q * m[l][1] + o * m[parseInt(l + 1, 10)][1]
					}
				}
				return [ m[0][0], m[0][1] ]
			}
		};
		g.Easing = {
			easeNone : function(l, k, n, m) {
				return n * l / m + k
			},
			easeIn : function(l, k, n, m) {
				return n * (l /= m) * l + k
			},
			easeOut : function(l, k, n, m) {
				return -n * (l /= m) * (l - 2) + k
			}
		};
		(function() {
			g.Motion = function(p, o, q, r) {
				if (p) {
					g.Motion.superclass.constructor.call(this, p, o, q, r)
				}
			};
			Ext.extend(g.Motion, Ext.lib.AnimBase);
			var n = g.Motion.superclass, m = g.Motion.prototype, l = /^points$/i;
			Ext
					.apply(
							g.Motion.prototype,
							{
								setAttr : function(o, s, r) {
									var q = this, p = n.setAttr;
									if (l.test(o)) {
										r = r || "px";
										p.call(q, "left", s[0], r);
										p.call(q, "top", s[1], r)
									} else {
										p.call(q, o, s, r)
									}
								},
								getAttr : function(o) {
									var q = this, p = n.getAttr;
									return l.test(o) ? [ p.call(q, "left"), p.call(q, "top") ] : p.call(q, o)
								},
								doMethod : function(o, r, p) {
									var q = this;
									return l.test(o) ? g.Bezier.getPosition(q.runAttrs[o], q.method(q.curFrame, 0, 100,
											q.totalFrames) / 100) : n.doMethod.call(q, o, r, p)
								},
								setRunAttr : function(v) {
									if (l.test(v)) {
										var x = this, q = this.el, A = this.attributes.points, t = A.control || [], y = A.from, z = A.to, w = A.by, B = g.Dom, p, s, r, u, o;
										if (t.length > 0 && !Ext.isArray(t[0])) {
											t = [ t ]
										} else {
										}
										Ext.fly(q, "_anim").position();
										B.setXY(q, j(y) ? y : B.getXY(q));
										p = x.getAttr("points");
										if (j(z)) {
											r = k.call(x, z, p);
											for (s = 0, u = t.length; s < u; ++s) {
												t[s] = k.call(x, t[s], p)
											}
										} else {
											if (j(w)) {
												r = [ p[0] + w[0], p[1] + w[1] ];
												for (s = 0, u = t.length; s < u; ++s) {
													t[s] = [ p[0] + t[s][0], p[1] + t[s][1] ]
												}
											}
										}
										o = this.runAttrs[v] = [ p ];
										if (t.length > 0) {
											o = o.concat(t)
										}
										o[o.length] = r
									} else {
										n.setRunAttr.call(this, v)
									}
								}
							});
			var k = function(o, q) {
				var p = g.Dom.getXY(this.el);
				return [ o[0] - p[0] + q[0], o[1] - p[1] + q[1] ]
			}
		})()
	})();
	(function() {
		var d = Math.abs, i = Math.PI, h = Math.asin, g = Math.pow, e = Math.sin, f = Ext.lib;
		Ext.apply(f.Easing, {
			easeBoth : function(k, j, m, l) {
				return ((k /= l / 2) < 1) ? m / 2 * k * k + j : -m / 2 * ((--k) * (k - 2) - 1) + j
			},
			easeInStrong : function(k, j, m, l) {
				return m * (k /= l) * k * k * k + j
			},
			easeOutStrong : function(k, j, m, l) {
				return -m * ((k = k / l - 1) * k * k * k - 1) + j
			},
			easeBothStrong : function(k, j, m, l) {
				return ((k /= l / 2) < 1) ? m / 2 * k * k * k * k + j : -m / 2 * ((k -= 2) * k * k * k - 2) + j
			},
			elasticIn : function(l, j, q, o, k, n) {
				if (l == 0 || (l /= o) == 1) {
					return l == 0 ? j : j + q
				}
				n = n || (o * 0.3);
				var m;
				if (k >= d(q)) {
					m = n / (2 * i) * h(q / k)
				} else {
					k = q;
					m = n / 4
				}
				return -(k * g(2, 10 * (l -= 1)) * e((l * o - m) * (2 * i) / n)) + j
			},
			elasticOut : function(l, j, q, o, k, n) {
				if (l == 0 || (l /= o) == 1) {
					return l == 0 ? j : j + q
				}
				n = n || (o * 0.3);
				var m;
				if (k >= d(q)) {
					m = n / (2 * i) * h(q / k)
				} else {
					k = q;
					m = n / 4
				}
				return k * g(2, -10 * l) * e((l * o - m) * (2 * i) / n) + q + j
			},
			elasticBoth : function(l, j, q, o, k, n) {
				if (l == 0 || (l /= o / 2) == 2) {
					return l == 0 ? j : j + q
				}
				n = n || (o * (0.3 * 1.5));
				var m;
				if (k >= d(q)) {
					m = n / (2 * i) * h(q / k)
				} else {
					k = q;
					m = n / 4
				}
				return l < 1 ? -0.5 * (k * g(2, 10 * (l -= 1)) * e((l * o - m) * (2 * i) / n)) + j : k
						* g(2, -10 * (l -= 1)) * e((l * o - m) * (2 * i) / n) * 0.5 + q + j
			},
			backIn : function(k, j, n, m, l) {
				l = l || 1.70158;
				return n * (k /= m) * k * ((l + 1) * k - l) + j
			},
			backOut : function(k, j, n, m, l) {
				if (!l) {
					l = 1.70158
				}
				return n * ((k = k / m - 1) * k * ((l + 1) * k + l) + 1) + j
			},
			backBoth : function(k, j, n, m, l) {
				l = l || 1.70158;
				return ((k /= m / 2) < 1) ? n / 2 * (k * k * (((l *= (1.525)) + 1) * k - l)) + j : n / 2
						* ((k -= 2) * k * (((l *= (1.525)) + 1) * k + l) + 2) + j
			},
			bounceIn : function(k, j, m, l) {
				return m - f.Easing.bounceOut(l - k, 0, m, l) + j
			},
			bounceOut : function(k, j, m, l) {
				if ((k /= l) < (1 / 2.75)) {
					return m * (7.5625 * k * k) + j
				} else {
					if (k < (2 / 2.75)) {
						return m * (7.5625 * (k -= (1.5 / 2.75)) * k + 0.75) + j
					} else {
						if (k < (2.5 / 2.75)) {
							return m * (7.5625 * (k -= (2.25 / 2.75)) * k + 0.9375) + j
						}
					}
				}
				return m * (7.5625 * (k -= (2.625 / 2.75)) * k + 0.984375) + j
			},
			bounceBoth : function(k, j, m, l) {
				return (k < l / 2) ? f.Easing.bounceIn(k * 2, 0, m, l) * 0.5 + j : f.Easing.bounceOut(k * 2 - l, 0, m,
						l)
						* 0.5 + m * 0.5 + j
			}
		})
	})();
	(function() {
		var h = Ext.lib;
		h.Anim.color = function(p, n, q, r, m, o) {
			return h.Anim.run(p, n, q, r, m, o, h.ColorAnim)
		};
		h.ColorAnim = function(n, m, o, p) {
			h.ColorAnim.superclass.constructor.call(this, n, m, o, p)
		};
		Ext.extend(h.ColorAnim, h.AnimBase);
		var j = h.ColorAnim.superclass, i = /color$/i, f = /^transparent|rgba\(0, 0, 0, 0\)$/, l = /^rgb\(([0-9]+)\s*,\s*([0-9]+)\s*,\s*([0-9]+)\)$/i, d = /^#?([0-9A-F]{2})([0-9A-F]{2})([0-9A-F]{2})$/i, e = /^#?([0-9A-F]{1})([0-9A-F]{1})([0-9A-F]{1})$/i, g = function(
				m) {
			return typeof m !== "undefined"
		};
		function k(n) {
			var p = parseInt, o, m = null, q;
			if (n.length == 3) {
				return n
			}
			Ext.each([ d, l, e ], function(s, r) {
				o = (r % 2 == 0) ? 16 : 10;
				q = s.exec(n);
				if (q && q.length == 4) {
					m = [ p(q[1], o), p(q[2], o), p(q[3], o) ];
					return false
				}
			});
			return m
		}
		Ext.apply(h.ColorAnim.prototype, {
			getAttr : function(m) {
				var o = this, n = o.el, p;
				if (i.test(m)) {
					while (n && f.test(p = Ext.fly(n).getStyle(m))) {
						n = n.parentNode;
						p = "fff"
					}
				} else {
					p = j.getAttr.call(o, m)
				}
				return p
			},
			doMethod : function(m, r, n) {
				var p = this, q, o = Math.floor;
				if (i.test(m)) {
					q = [];
					Ext.each(r, function(s, t) {
						q[t] = j.doMethod.call(p, m, s, n[t])
					});
					q = "rgb(" + o(q[0]) + "," + o(q[1]) + "," + o(q[2]) + ")"
				} else {
					q = j.doMethod.call(p, m, r, n)
				}
				return q
			},
			setRunAttr : function(m) {
				var p = this, o = p.attributes[m], t = o.to, q = o.by, r;
				j.setRunAttr.call(p, m);
				r = p.runAttrs[m];
				if (i.test(m)) {
					var s = k(r.start), n = k(r.end);
					if (!g(t) && g(q)) {
						n = k(q);
						Ext.each(s, function(v, u) {
							n[u] = v + n[u]
						})
					}
					r.start = s;
					r.end = n
				}
			}
		})
	})();
	(function() {
		var d = Ext.lib;
		d.Anim.scroll = function(j, h, k, l, g, i) {
			return d.Anim.run(j, h, k, l, g, i, d.Scroll)
		};
		d.Scroll = function(h, g, i, j) {
			if (h) {
				d.Scroll.superclass.constructor.call(this, h, g, i, j)
			}
		};
		Ext.extend(d.Scroll, d.ColorAnim);
		var f = d.Scroll.superclass, e = "scroll";
		Ext.apply(d.Scroll.prototype, {
			doMethod : function(g, m, h) {
				var k, j = this, l = j.curFrame, i = j.totalFrames;
				if (g == e) {
					k = [ j.method(l, m[0], h[0] - m[0], i), j.method(l, m[1], h[1] - m[1], i) ]
				} else {
					k = f.doMethod.call(j, g, m, h)
				}
				return k
			},
			getAttr : function(g) {
				var h = this;
				if (g == e) {
					return [ h.el.scrollLeft, h.el.scrollTop ]
				} else {
					return f.getAttr.call(h, g)
				}
			},
			setAttr : function(g, j, i) {
				var h = this;
				if (g == e) {
					h.el.scrollLeft = j[0];
					h.el.scrollTop = j[1]
				} else {
					f.setAttr.call(h, g, j, i)
				}
			}
		})
	})();
	if (Ext.isIE) {
		function a() {
			var d = Function.prototype;
			delete d.createSequence;
			delete d.defer;
			delete d.createDelegate;
			delete d.createCallback;
			delete d.createInterceptor;
			window.detachEvent("onunload", a)
		}
		window.attachEvent("onunload", a)
	}
})();

Ext.DomHelper = function() {
	var s = null, j = /^(?:br|frame|hr|img|input|link|meta|range|spacer|wbr|area|param|col)$/i, l = /^table|tbody|tr|td$/i, p, m = "afterbegin", n = "afterend", c = "beforebegin", o = "beforeend", a = "<table>", h = "</table>", b = a
			+ "<tbody>", i = "</tbody>" + h, k = b + "<tr>", r = "</tr>" + i;
	function g(w, y, x, z, v, t) {
		var u = p.insertHtml(z, Ext.getDom(w), q(y));
		return x ? Ext.get(u, true) : u
	}
	function q(y) {
		var v = "", u, x, w, t, z;
		if (typeof y == "string") {
			v = y
		} else {
			if (Ext.isArray(y)) {
				Ext.each(y, function(A) {
					v += q(A)
				})
			} else {
				v += "<" + (y.tag = y.tag || "div");
				Ext.iterate(y, function(A, B) {
					if (!/tag|children|cn|html$/i.test(A)) {
						if (Ext.isObject(B)) {
							v += " " + A + "='";
							Ext.iterate(B, function(D, C) {
								v += D + ":" + C + ";"
							});
							v += "'"
						} else {
							v += " " + ({
								cls : "class",
								htmlFor : "for"
							}[A] || A) + "='" + B + "'"
						}
					}
				});
				if (j.test(y.tag)) {
					v += "/>"
				} else {
					v += ">";
					if ((z = y.children || y.cn)) {
						v += q(z)
					} else {
						if (y.html) {
							v += y.html
						}
					}
					v += "</" + y.tag + ">"
				}
			}
		}
		return v
	}
	function e(y, w, v, x) {
		s.innerHTML = [ w, v, x ].join("");
		var t = -1, u = s;
		while (++t < y) {
			u = u.firstChild
		}
		return u
	}
	function d(t, u, w, v) {
		var x, y;
		s = s || document.createElement("div");
		if (t == "td" && (u == m || u == o) || !/td|tr|tbody/i.test(t) && (u == c || u == n)) {
			return
		}
		y = u == c ? w : u == n ? w.nextSibling : u == m ? w.firstChild : null;
		if (u == c || u == n) {
			w = w.parentNode
		}
		if (t == "td" || (t == "tr" && (u == o || u == m))) {
			x = e(4, k, v, r)
		} else {
			if ((t == "tbody" && (u == o || u == m)) || (t == "tr" && (u == c || u == n))) {
				x = e(3, b, v, i)
			} else {
				x = e(2, a, v, h)
			}
		}
		w.insertBefore(x, y);
		return x
	}
	p = {
		markup : function(t) {
			return q(t)
		},
		insertHtml : function(y, t, z) {
			var x = {}, v, B, A, C, w, u;
			y = y.toLowerCase();
			x[c] = [ "BeforeBegin", "previousSibling" ];
			x[n] = [ "AfterEnd", "nextSibling" ];
			if (t.insertAdjacentHTML) {
				if (l.test(t.tagName) && (u = d(t.tagName.toLowerCase(), y, t, z))) {
					return u
				}
				x[m] = [ "AfterBegin", "firstChild" ];
				x[o] = [ "BeforeEnd", "lastChild" ];
				if ((v = x[y])) {
					t.insertAdjacentHTML(v[0], z);
					return t[v[1]]
				}
			} else {
				A = t.ownerDocument.createRange();
				B = "setStart" + (/end/i.test(y) ? "After" : "Before");
				if (x[y]) {
					A[B](t);
					C = A.createContextualFragment(z);
					t.parentNode.insertBefore(C, y == c ? t : t.nextSibling);
					return t[(y == c ? "previous" : "next") + "Sibling"]
				} else {
					w = (y == m ? "first" : "last") + "Child";
					if (t.firstChild) {
						A[B](t[w]);
						C = A.createContextualFragment(z);
						if (y == m) {
							t.insertBefore(C, t.firstChild)
						} else {
							t.appendChild(C)
						}
					} else {
						t.innerHTML = z
					}
					return t[w]
				}
			}
			throw 'Illegal insertion point -> "' + y + '"'
		},
		insertBefore : function(t, v, u) {
			return g(t, v, u, c)
		},
		insertAfter : function(t, v, u) {
			return g(t, v, u, n, "nextSibling")
		},
		insertFirst : function(t, v, u) {
			return g(t, v, u, m, "firstChild")
		},
		append : function(t, v, u) {
			return g(t, v, u, o, "", true)
		},
		overwrite : function(t, v, u) {
			t = Ext.getDom(t);
			t.innerHTML = q(v);
			return u ? Ext.get(t.firstChild) : t.firstChild
		},
		createHtml : q
	};
	return p
}();
Ext.apply(Ext.DomHelper, function() {
	var d, a = "afterbegin", g = "afterend", h = "beforebegin", c = "beforeend";
	function e(l, n, m, p, k, i) {
		l = Ext.getDom(l);
		var j;
		if (d.useDom) {
			j = b(n, null);
			if (i) {
				l.appendChild(j)
			} else {
				(k == "firstChild" ? l : l.parentNode).insertBefore(j, l[k] || l)
			}
		} else {
			j = Ext.DomHelper.insertHtml(p, l, Ext.DomHelper.createHtml(n))
		}
		return m ? Ext.get(j, true) : j
	}
	function b(p, j) {
		var l, m = document, k, i, n, q;
		if (Ext.isArray(p)) {
			l = m.createDocumentFragment();
			Ext.each(p, function(o) {
				b(o, l)
			})
		} else {
			if (Ext.isString(p)) {
				l = m.createTextNode(p)
			} else {
				l = m.createElement(p.tag || "div");
				k = !!l.setAttribute;
				Ext.iterate(p, function(o, r) {
					if (!/tag|children|cn|html|style/.test(o)) {
						if (o == "cls") {
							l.className = r
						} else {
							if (k) {
								l.setAttribute(o, r)
							} else {
								l[o] = r
							}
						}
					}
				});
				d.applyStyles(l, p.style);
				if ((q = p.children || p.cn)) {
					b(q, l)
				} else {
					if (p.html) {
						l.innerHTML = p.html
					}
				}
			}
		}
		if (j) {
			j.appendChild(l)
		}
		return l
	}
	d = {
		createTemplate : function(j) {
			var i = Ext.DomHelper.createHtml(j);
			return new Ext.Template(i)
		},
		useDom : false,
		applyStyles : function(m, n) {
			if (n) {
				var k = 0, j, l;
				m = Ext.fly(m);
				if (Ext.isFunction(n)) {
					n = n.call()
				}
				if (Ext.isString(n)) {
					n = n.trim().split(/\s*(?::|;)\s*/);
					for (j = n.length; k < j;) {
						m.setStyle(n[k++], n[k++])
					}
				} else {
					if (Ext.isObject(n)) {
						m.setStyle(n)
					}
				}
			}
		},
		insertBefore : function(i, k, j) {
			return e(i, k, j, h)
		},
		insertAfter : function(i, k, j) {
			return e(i, k, j, g, "nextSibling")
		},
		insertFirst : function(i, k, j) {
			return e(i, k, j, a, "firstChild")
		},
		append : function(i, k, j) {
			return e(i, k, j, c, "", true)
		},
		createDom : b
	};
	return d
}());
Ext.Template = function(d) {
	var e = this, b = arguments, c = [];
	if (Ext.isArray(d)) {
		d = d.join("")
	} else {
		if (b.length > 1) {
			Ext.each(b, function(a) {
				if (Ext.isObject(a)) {
					Ext.apply(e, a)
				} else {
					c.push(a)
				}
			});
			d = c.join("")
		}
	}
	e.html = d;
	if (e.compiled) {
		e.compile()
	}
};
Ext.Template.prototype = {
	applyTemplate : function(a) {
		var b = this;
		return b.compiled ? b.compiled(a) : b.html.replace(b.re, function(c, d) {
			return a[d] !== undefined ? a[d] : ""
		})
	},
	set : function(a, c) {
		var b = this;
		b.html = a;
		b.compiled = null;
		return c ? b.compile() : b
	},
	re : /\{([\w-]+)\}/g,
	compile : function() {
		var me = this, sep = Ext.isGecko ? "+" : ",";
		function fn(m, name) {
			name = "values['" + name + "']";
			return "'" + sep + "(" + name + " == undefined ? '' : " + name + ")" + sep + "'"
		}
		eval("this.compiled = function(values){ return " + (Ext.isGecko ? "'" : "['")
				+ me.html.replace(/\\/g, "\\\\").replace(/(\r\n|\n)/g, "\\n").replace(/'/g, "\\'").replace(this.re, fn)
				+ (Ext.isGecko ? "';};" : "'].join('');};"));
		return me
	},
	insertFirst : function(b, a, c) {
		return this.doInsert("afterBegin", b, a, c)
	},
	insertBefore : function(b, a, c) {
		return this.doInsert("beforeBegin", b, a, c)
	},
	insertAfter : function(b, a, c) {
		return this.doInsert("afterEnd", b, a, c)
	},
	append : function(b, a, c) {
		return this.doInsert("beforeEnd", b, a, c)
	},
	doInsert : function(c, e, b, a) {
		e = Ext.getDom(e);
		var d = Ext.DomHelper.insertHtml(c, e, this.applyTemplate(b));
		return a ? Ext.get(d, true) : d
	},
	overwrite : function(b, a, c) {
		b = Ext.getDom(b);
		b.innerHTML = this.applyTemplate(a);
		return c ? Ext.get(b.firstChild, true) : b.firstChild
	}
};
Ext.Template.prototype.apply = Ext.Template.prototype.applyTemplate;
Ext.Template.from = function(b, a) {
	b = Ext.getDom(b);
	return new Ext.Template(b.value || b.innerHTML, a || "")
};
Ext.apply(Ext.Template.prototype, {
	applyTemplate : function(b) {
		var g = this, a = g.disableFormats !== true, e = Ext.util.Format, c = g;
		if (g.compiled) {
			return g.compiled(b)
		}
		function d(j, l, p, k) {
			if (p && a) {
				if (p.substr(0, 5) == "this.") {
					return c.call(p.substr(5), b[l], b)
				} else {
					if (k) {
						var o = /^\s*['"](.*)["']\s*$/;
						k = k.split(",");
						for ( var n = 0, h = k.length; n < h; n++) {
							k[n] = k[n].replace(o, "$1")
						}
						k = [ b[l] ].concat(k)
					} else {
						k = [ b[l] ]
					}
					return e[p].apply(e, k)
				}
			} else {
				return b[l] !== undefined ? b[l] : ""
			}
		}
		return g.html.replace(g.re, d)
	},
	disableFormats : false,
	re : /\{([\w-]+)(?:\:([\w\.]*)(?:\((.*?)?\))?)?\}/g,
	compile : function() {
		var me = this, fm = Ext.util.Format, useF = me.disableFormats !== true, sep = Ext.isGecko ? "+" : ",", body;
		function fn(m, name, format, args) {
			if (format && useF) {
				args = args ? "," + args : "";
				if (format.substr(0, 5) != "this.") {
					format = "fm." + format + "("
				} else {
					format = 'this.call("' + format.substr(5) + '", ';
					args = ", values"
				}
			} else {
				args = "";
				format = "(values['" + name + "'] == undefined ? '' : "
			}
			return "'" + sep + format + "values['" + name + "']" + args + ")" + sep + "'"
		}
		if (Ext.isGecko) {
			body = "this.compiled = function(values){ return '"
					+ me.html.replace(/\\/g, "\\\\").replace(/(\r\n|\n)/g, "\\n").replace(/'/g, "\\'").replace(this.re,
							fn) + "';};"
		} else {
			body = [ "this.compiled = function(values){ return ['" ];
			body.push(me.html.replace(/\\/g, "\\\\").replace(/(\r\n|\n)/g, "\\n").replace(/'/g, "\\'").replace(this.re,
					fn));
			body.push("'].join('');};");
			body = body.join("")
		}
		eval(body);
		return me
	},
	call : function(c, b, a) {
		return this[c](b, a)
	}
});
Ext.Template.prototype.apply = Ext.Template.prototype.applyTemplate;
Ext.DomQuery = function() {
	var cache = {}, simpleCache = {}, valueCache = {}, nonSpace = /\S/, trimRe = /^\s+|\s+$/g, tplRe = /\{(\d+)\}/g, modeRe = /^(\s?[\/>+~]\s?|\s|$)/, tagTokenRe = /^(#)?([\w-\*]+)/, nthRe = /(\d*)n\+?(\d*)/, nthRe2 = /\D/, isIE = window.ActiveXObject ? true
			: false, isOpera = Ext.isOpera, key = 30803;
	eval("var batch = 30803;");
	function child(p, index) {
		var i = 0, n = p.firstChild;
		while (n) {
			if (n.nodeType == 1) {
				if (++i == index) {
					return n
				}
			}
			n = n.nextSibling
		}
		return null
	}
	function next(n) {
		while ((n = n.nextSibling) && n.nodeType != 1) {
		}
		return n
	}
	function prev(n) {
		while ((n = n.previousSibling) && n.nodeType != 1) {
		}
		return n
	}
	function children(d) {
		var n = d.firstChild, ni = -1, nx;
		while (n) {
			nx = n.nextSibling;
			if (n.nodeType == 3 && !nonSpace.test(n.nodeValue)) {
				d.removeChild(n)
			} else {
				n.nodeIndex = ++ni
			}
			n = nx
		}
		return this
	}
	function byClassName(c, a, v) {
		if (!v) {
			return c
		}
		var r = [], ri = -1, cn;
		for ( var i = 0, ci; ci = c[i]; i++) {
			if ((" " + ci.className + " ").indexOf(v) != -1) {
				r[++ri] = ci
			}
		}
		return r
	}
	function attrValue(n, attr) {
		if (!n.tagName && typeof n.length != "undefined") {
			n = n[0]
		}
		if (!n) {
			return null
		}
		if (attr == "for") {
			return n.htmlFor
		}
		if (attr == "class" || attr == "className") {
			return n.className
		}
		return n.getAttribute(attr) || n[attr]
	}
	function getNodes(ns, mode, tagName) {
		var result = [], ri = -1, cs;
		if (!ns) {
			return result
		}
		tagName = tagName || "*";
		if (typeof ns.getElementsByTagName != "undefined") {
			ns = [ ns ]
		}
		if (!mode) {
			for ( var i = 0, ni; ni = ns[i]; i++) {
				cs = ni.getElementsByTagName(tagName);
				for ( var j = 0, ci; ci = cs[j]; j++) {
					result[++ri] = ci
				}
			}
		} else {
			if (mode == "/" || mode == ">") {
				var utag = tagName.toUpperCase();
				for ( var i = 0, ni, cn; ni = ns[i]; i++) {
					cn = isOpera ? ni.childNodes : (ni.children || ni.childNodes);
					for ( var j = 0, cj; cj = cn[j]; j++) {
						if (cj.nodeName == utag || cj.nodeName == tagName || tagName == "*") {
							result[++ri] = cj
						}
					}
				}
			} else {
				if (mode == "+") {
					var utag = tagName.toUpperCase();
					for ( var i = 0, n; n = ns[i]; i++) {
						while ((n = n.nextSibling) && n.nodeType != 1) {
						}
						if (n && (n.nodeName == utag || n.nodeName == tagName || tagName == "*")) {
							result[++ri] = n
						}
					}
				} else {
					if (mode == "~") {
						var utag = tagName.toUpperCase();
						for ( var i = 0, n; n = ns[i]; i++) {
							while ((n = n.nextSibling)) {
								if (n.nodeName == utag || n.nodeName == tagName || tagName == "*") {
									result[++ri] = n
								}
							}
						}
					}
				}
			}
		}
		return result
	}
	function concat(a, b) {
		if (b.slice) {
			return a.concat(b)
		}
		for ( var i = 0, l = b.length; i < l; i++) {
			a[a.length] = b[i]
		}
		return a
	}
	function byTag(cs, tagName) {
		if (cs.tagName || cs == document) {
			cs = [ cs ]
		}
		if (!tagName) {
			return cs
		}
		var r = [], ri = -1;
		tagName = tagName.toLowerCase();
		for ( var i = 0, ci; ci = cs[i]; i++) {
			if (ci.nodeType == 1 && ci.tagName.toLowerCase() == tagName) {
				r[++ri] = ci
			}
		}
		return r
	}
	function byId(cs, attr, id) {
		if (cs.tagName || cs == document) {
			cs = [ cs ]
		}
		if (!id) {
			return cs
		}
		var r = [], ri = -1;
		for ( var i = 0, ci; ci = cs[i]; i++) {
			if (ci && ci.id == id) {
				r[++ri] = ci;
				return r
			}
		}
		return r
	}
	function byAttribute(cs, attr, value, op, custom) {
		var r = [], ri = -1, st = custom == "{", f = Ext.DomQuery.operators[op];
		for ( var i = 0, ci; ci = cs[i]; i++) {
			if (ci.nodeType != 1) {
				continue
			}
			var a;
			if (st) {
				a = Ext.DomQuery.getStyle(ci, attr)
			} else {
				if (attr == "class" || attr == "className") {
					a = ci.className
				} else {
					if (attr == "for") {
						a = ci.htmlFor
					} else {
						if (attr == "href") {
							a = ci.getAttribute("href", 2)
						} else {
							a = ci.getAttribute(attr)
						}
					}
				}
			}
			if ((f && f(a, value)) || (!f && a)) {
				r[++ri] = ci
			}
		}
		return r
	}
	function byPseudo(cs, name, value) {
		return Ext.DomQuery.pseudos[name](cs, value)
	}
	function nodupIEXml(cs) {
		var d = ++key, r;
		cs[0].setAttribute("_nodup", d);
		r = [ cs[0] ];
		for ( var i = 1, len = cs.length; i < len; i++) {
			var c = cs[i];
			if (!c.getAttribute("_nodup") != d) {
				c.setAttribute("_nodup", d);
				r[r.length] = c
			}
		}
		for ( var i = 0, len = cs.length; i < len; i++) {
			cs[i].removeAttribute("_nodup")
		}
		return r
	}
	function nodup(cs) {
		if (!cs) {
			return []
		}
		var len = cs.length, c, i, r = cs, cj, ri = -1;
		if (!len || typeof cs.nodeType != "undefined" || len == 1) {
			return cs
		}
		if (isIE && typeof cs[0].selectSingleNode != "undefined") {
			return nodupIEXml(cs)
		}
		var d = ++key;
		cs[0]._nodup = d;
		for (i = 1; c = cs[i]; i++) {
			if (c._nodup != d) {
				c._nodup = d
			} else {
				r = [];
				for ( var j = 0; j < i; j++) {
					r[++ri] = cs[j]
				}
				for (j = i + 1; cj = cs[j]; j++) {
					if (cj._nodup != d) {
						cj._nodup = d;
						r[++ri] = cj
					}
				}
				return r
			}
		}
		return r
	}
	function quickDiffIEXml(c1, c2) {
		var d = ++key, r = [];
		for ( var i = 0, len = c1.length; i < len; i++) {
			c1[i].setAttribute("_qdiff", d)
		}
		for ( var i = 0, len = c2.length; i < len; i++) {
			if (c2[i].getAttribute("_qdiff") != d) {
				r[r.length] = c2[i]
			}
		}
		for ( var i = 0, len = c1.length; i < len; i++) {
			c1[i].removeAttribute("_qdiff")
		}
		return r
	}
	function quickDiff(c1, c2) {
		var len1 = c1.length, d = ++key, r = [];
		if (!len1) {
			return c2
		}
		if (isIE && c1[0].selectSingleNode) {
			return quickDiffIEXml(c1, c2)
		}
		for ( var i = 0; i < len1; i++) {
			c1[i]._qdiff = d
		}
		for ( var i = 0, len = c2.length; i < len; i++) {
			if (c2[i]._qdiff != d) {
				r[r.length] = c2[i]
			}
		}
		return r
	}
	function quickId(ns, mode, root, id) {
		if (ns == root) {
			var d = root.ownerDocument || root;
			return d.getElementById(id)
		}
		ns = getNodes(ns, mode, "*");
		return byId(ns, null, id)
	}
	return {
		getStyle : function(el, name) {
			return Ext.fly(el).getStyle(name)
		},
		compile : function(path, type) {
			type = type || "select";
			var fn = [ "var f = function(root){\n var mode; ++batch; var n = root || document;\n" ], q = path, mode, lq, tk = Ext.DomQuery.matchers, tklen = tk.length, mm, lmode = q
					.match(modeRe);
			if (lmode && lmode[1]) {
				fn[fn.length] = 'mode="' + lmode[1].replace(trimRe, "") + '";';
				q = q.replace(lmode[1], "")
			}
			while (path.substr(0, 1) == "/") {
				path = path.substr(1)
			}
			while (q && lq != q) {
				lq = q;
				var tm = q.match(tagTokenRe);
				if (type == "select") {
					if (tm) {
						if (tm[1] == "#") {
							fn[fn.length] = 'n = quickId(n, mode, root, "' + tm[2] + '");'
						} else {
							fn[fn.length] = 'n = getNodes(n, mode, "' + tm[2] + '");'
						}
						q = q.replace(tm[0], "")
					} else {
						if (q.substr(0, 1) != "@") {
							fn[fn.length] = 'n = getNodes(n, mode, "*");'
						}
					}
				} else {
					if (tm) {
						if (tm[1] == "#") {
							fn[fn.length] = 'n = byId(n, null, "' + tm[2] + '");'
						} else {
							fn[fn.length] = 'n = byTag(n, "' + tm[2] + '");'
						}
						q = q.replace(tm[0], "")
					}
				}
				while (!(mm = q.match(modeRe))) {
					var matched = false;
					for ( var j = 0; j < tklen; j++) {
						var t = tk[j];
						var m = q.match(t.re);
						if (m) {
							fn[fn.length] = t.select.replace(tplRe, function(x, i) {
								return m[i]
							});
							q = q.replace(m[0], "");
							matched = true;
							break
						}
					}
					if (!matched) {
						throw 'Error parsing selector, parsing failed at "' + q + '"'
					}
				}
				if (mm[1]) {
					fn[fn.length] = 'mode="' + mm[1].replace(trimRe, "") + '";';
					q = q.replace(mm[1], "")
				}
			}
			fn[fn.length] = "return nodup(n);\n}";
			eval(fn.join(""));
			return f
		},
		select : function(path, root, type) {
			if (!root || root == document) {
				root = document
			}
			if (typeof root == "string") {
				root = document.getElementById(root)
			}
			var paths = path.split(","), results = [];
			for ( var i = 0, len = paths.length; i < len; i++) {
				var p = paths[i].replace(trimRe, "");
				if (!cache[p]) {
					cache[p] = Ext.DomQuery.compile(p);
					if (!cache[p]) {
						throw p + " is not a valid selector"
					}
				}
				var result = cache[p](root);
				if (result && result != document) {
					results = results.concat(result)
				}
			}
			if (paths.length > 1) {
				return nodup(results)
			}
			return results
		},
		selectNode : function(path, root) {
			return Ext.DomQuery.select(path, root)[0]
		},
		selectValue : function(path, root, defaultValue) {
			path = path.replace(trimRe, "");
			if (!valueCache[path]) {
				valueCache[path] = Ext.DomQuery.compile(path, "select")
			}
			var n = valueCache[path](root), v;
			n = n[0] ? n[0] : n;
			v = (n && n.firstChild ? n.firstChild.nodeValue : null);
			return ((v === null || v === undefined || v === "") ? defaultValue : v)
		},
		selectNumber : function(path, root, defaultValue) {
			var v = Ext.DomQuery.selectValue(path, root, defaultValue || 0);
			return parseFloat(v)
		},
		is : function(el, ss) {
			if (typeof el == "string") {
				el = document.getElementById(el)
			}
			var isArray = Ext.isArray(el), result = Ext.DomQuery.filter(isArray ? el : [ el ], ss);
			return isArray ? (result.length == el.length) : (result.length > 0)
		},
		filter : function(els, ss, nonMatches) {
			ss = ss.replace(trimRe, "");
			if (!simpleCache[ss]) {
				simpleCache[ss] = Ext.DomQuery.compile(ss, "simple")
			}
			var result = simpleCache[ss](els);
			return nonMatches ? quickDiff(result, els) : result
		},
		matchers : [ {
			re : /^\.([\w-]+)/,
			select : 'n = byClassName(n, null, " {1} ");'
		}, {
			re : /^\:([\w-]+)(?:\(((?:[^\s>\/]*|.*?))\))?/,
			select : 'n = byPseudo(n, "{1}", "{2}");'
		}, {
			re : /^(?:([\[\{])(?:@)?([\w-]+)\s?(?:(=|.=)\s?['"]?(.*?)["']?)?[\]\}])/,
			select : 'n = byAttribute(n, "{2}", "{4}", "{3}", "{1}");'
		}, {
			re : /^#([\w-]+)/,
			select : 'n = byId(n, null, "{1}");'
		}, {
			re : /^@([\w-]+)/,
			select : 'return {firstChild:{nodeValue:attrValue(n, "{1}")}};'
		} ],
		operators : {
			"=" : function(a, v) {
				return a == v
			},
			"!=" : function(a, v) {
				return a != v
			},
			"^=" : function(a, v) {
				return a && a.substr(0, v.length) == v
			},
			"$=" : function(a, v) {
				return a && a.substr(a.length - v.length) == v
			},
			"*=" : function(a, v) {
				return a && a.indexOf(v) !== -1
			},
			"%=" : function(a, v) {
				return (a % v) == 0
			},
			"|=" : function(a, v) {
				return a && (a == v || a.substr(0, v.length + 1) == v + "-")
			},
			"~=" : function(a, v) {
				return a && (" " + a + " ").indexOf(" " + v + " ") != -1
			}
		},
		pseudos : {
			"first-child" : function(c) {
				var r = [], ri = -1, n;
				for ( var i = 0, ci; ci = n = c[i]; i++) {
					while ((n = n.previousSibling) && n.nodeType != 1) {
					}
					if (!n) {
						r[++ri] = ci
					}
				}
				return r
			},
			"last-child" : function(c) {
				var r = [], ri = -1, n;
				for ( var i = 0, ci; ci = n = c[i]; i++) {
					while ((n = n.nextSibling) && n.nodeType != 1) {
					}
					if (!n) {
						r[++ri] = ci
					}
				}
				return r
			},
			"nth-child" : function(c, a) {
				var r = [], ri = -1, m = nthRe.exec(a == "even" && "2n" || a == "odd" && "2n+1" || !nthRe2.test(a)
						&& "n+" + a || a), f = (m[1] || 1) - 0, l = m[2] - 0;
				for ( var i = 0, n; n = c[i]; i++) {
					var pn = n.parentNode;
					if (batch != pn._batch) {
						var j = 0;
						for ( var cn = pn.firstChild; cn; cn = cn.nextSibling) {
							if (cn.nodeType == 1) {
								cn.nodeIndex = ++j
							}
						}
						pn._batch = batch
					}
					if (f == 1) {
						if (l == 0 || n.nodeIndex == l) {
							r[++ri] = n
						}
					} else {
						if ((n.nodeIndex + l) % f == 0) {
							r[++ri] = n
						}
					}
				}
				return r
			},
			"only-child" : function(c) {
				var r = [], ri = -1;
				for ( var i = 0, ci; ci = c[i]; i++) {
					if (!prev(ci) && !next(ci)) {
						r[++ri] = ci
					}
				}
				return r
			},
			empty : function(c) {
				var r = [], ri = -1;
				for ( var i = 0, ci; ci = c[i]; i++) {
					var cns = ci.childNodes, j = 0, cn, empty = true;
					while (cn = cns[j]) {
						++j;
						if (cn.nodeType == 1 || cn.nodeType == 3) {
							empty = false;
							break
						}
					}
					if (empty) {
						r[++ri] = ci
					}
				}
				return r
			},
			contains : function(c, v) {
				var r = [], ri = -1;
				for ( var i = 0, ci; ci = c[i]; i++) {
					if ((ci.textContent || ci.innerText || "").indexOf(v) != -1) {
						r[++ri] = ci
					}
				}
				return r
			},
			nodeValue : function(c, v) {
				var r = [], ri = -1;
				for ( var i = 0, ci; ci = c[i]; i++) {
					if (ci.firstChild && ci.firstChild.nodeValue == v) {
						r[++ri] = ci
					}
				}
				return r
			},
			checked : function(c) {
				var r = [], ri = -1;
				for ( var i = 0, ci; ci = c[i]; i++) {
					if (ci.checked == true) {
						r[++ri] = ci
					}
				}
				return r
			},
			not : function(c, ss) {
				return Ext.DomQuery.filter(c, ss, true)
			},
			any : function(c, selectors) {
				var ss = selectors.split("|"), r = [], ri = -1, s;
				for ( var i = 0, ci; ci = c[i]; i++) {
					for ( var j = 0; s = ss[j]; j++) {
						if (Ext.DomQuery.is(ci, s)) {
							r[++ri] = ci;
							break
						}
					}
				}
				return r
			},
			odd : function(c) {
				return this["nth-child"](c, "odd")
			},
			even : function(c) {
				return this["nth-child"](c, "even")
			},
			nth : function(c, a) {
				return c[a - 1] || []
			},
			first : function(c) {
				return c[0] || []
			},
			last : function(c) {
				return c[c.length - 1] || []
			},
			has : function(c, ss) {
				var s = Ext.DomQuery.select, r = [], ri = -1;
				for ( var i = 0, ci; ci = c[i]; i++) {
					if (s(ss, ci).length > 0) {
						r[++ri] = ci
					}
				}
				return r
			},
			next : function(c, ss) {
				var is = Ext.DomQuery.is, r = [], ri = -1;
				for ( var i = 0, ci; ci = c[i]; i++) {
					var n = next(ci);
					if (n && is(n, ss)) {
						r[++ri] = ci
					}
				}
				return r
			},
			prev : function(c, ss) {
				var is = Ext.DomQuery.is, r = [], ri = -1;
				for ( var i = 0, ci; ci = c[i]; i++) {
					var n = prev(ci);
					if (n && is(n, ss)) {
						r[++ri] = ci
					}
				}
				return r
			}
		}
	}
}();
Ext.query = Ext.DomQuery.select;
(function() {
	var i = Ext.util, l = Ext.toArray, k = Ext.each, a = Ext.isObject, h = true, j = false;
	i.Observable = function() {
		var m = this, n = m.events;
		if (m.listeners) {
			m.on(m.listeners);
			delete m.listeners
		}
		m.events = n || {}
	};
	i.Observable.prototype = function() {
		var n = /^(?:scope|delay|buffer|single)$/, m = function(o) {
			return o.toLowerCase()
		};
		return {
			fireEvent : function() {
				var o = l(arguments), r = m(o[0]), s = this, p = h, u = s.events[r], t, v;
				if (s.eventsSuspended === h) {
					if (t = s.suspendedEventsQueue) {
						t.push(o)
					}
				} else {
					if (a(u) && u.bubble) {
						if (u.fire.apply(u, o.slice(1)) === j) {
							return j
						}
						v = s.getBubbleTarget && s.getBubbleTarget();
						if (v && v.enableBubble) {
							v.enableBubble(r);
							return v.fireEvent.apply(v, o)
						}
					} else {
						if (a(u)) {
							o.shift();
							p = u.fire.apply(u, o)
						}
					}
				}
				return p
			},
			addListener : function(r, u, w, q) {
				var t = this, s, x, v, p;
				if (a(r)) {
					q = r;
					for (s in q) {
						x = q[s];
						if (!n.test(s)) {
							t.addListener(s, x.fn || x, x.scope || q.scope, x.fn ? x : q)
						}
					}
				} else {
					r = m(r);
					p = t.events[r] || h;
					if (typeof p == "boolean") {
						t.events[r] = p = new i.Event(t, r)
					}
					p.addListener(u, w, a(q) ? q : {})
				}
			},
			removeListener : function(o, q, p) {
				var r = this.events[m(o)];
				if (a(r)) {
					r.removeListener(q, p)
				}
			},
			purgeListeners : function() {
				var q = this.events, o, p;
				for (p in q) {
					o = q[p];
					if (a(o)) {
						o.clearListeners()
					}
				}
			},
			addEvents : function(q) {
				var p = this;
				p.events = p.events || {};
				if (typeof q == "string") {
					k(arguments, function(o) {
						p.events[o] = p.events[o] || h
					})
				} else {
					Ext.applyIf(p.events, q)
				}
			},
			hasListener : function(o) {
				var p = this.events[o];
				return a(p) && p.listeners.length > 0
			},
			suspendEvents : function(o) {
				this.eventsSuspended = h;
				if (o) {
					this.suspendedEventsQueue = []
				}
			},
			resumeEvents : function() {
				var o = this;
				o.eventsSuspended = !delete o.suspendedEventQueue;
				k(o.suspendedEventsQueue, function(p) {
					o.fireEvent.apply(o, p)
				})
			}
		}
	}();
	var e = i.Observable.prototype;
	e.on = e.addListener;
	e.un = e.removeListener;
	i.Observable.releaseCapture = function(m) {
		m.fireEvent = e.fireEvent
	};
	function g(n, p, m) {
		return function() {
			if (p.target == arguments[0]) {
				n.apply(m, l(arguments))
			}
		}
	}
	function c(p, q, n) {
		var m = new i.DelayedTask();
		return function() {
			m.delay(q.buffer, p, n, l(arguments))
		}
	}
	function d(o, p, n, m) {
		return function() {
			p.removeListener(n, m);
			return o.apply(m, arguments)
		}
	}
	function b(n, p, m) {
		return function() {
			var o = l(arguments);
			(function() {
				n.apply(m, o)
			}).defer(p.delay || 10)
		}
	}
	i.Event = function(n, m) {
		this.name = m;
		this.obj = n;
		this.listeners = []
	};
	i.Event.prototype = {
		addListener : function(p, o, n) {
			var q = this, m;
			o = o || q.obj;
			if (!q.isListening(p, o)) {
				m = q.createListener(p, o, n);
				if (q.firing) {
					q.listeners = q.listeners.slice(0)
				}
				q.listeners.push(m)
			}
		},
		createListener : function(q, p, r) {
			r = r || {}, p = p || this.obj;
			var m = {
				fn : q,
				scope : p,
				options : r
			}, n = q;
			if (r.target) {
				n = g(n, r, p)
			}
			if (r.delay) {
				n = b(n, r, p)
			}
			if (r.single) {
				n = d(n, this, q, p)
			}
			if (r.buffer) {
				n = c(n, r, p)
			}
			m.fireFn = n;
			return m
		},
		findListener : function(p, o) {
			var n, m = -1;
			k(this.listeners, function(q, r) {
				n = q.scope;
				if (q.fn == p && (n == o || n == this.obj)) {
					m = r;
					return j
				}
			}, this);
			return m
		},
		isListening : function(n, m) {
			return this.findListener(n, m) != -1
		},
		removeListener : function(p, o) {
			var n, q = this, m = j;
			if ((n = q.findListener(p, o)) != -1) {
				if (q.firing) {
					q.listeners = q.listeners.slice(0)
				}
				q.listeners.splice(n, 1);
				m = h
			}
			return m
		},
		clearListeners : function() {
			this.listeners = []
		},
		fire : function() {
			var o = this, n = l(arguments), m = h;
			k(o.listeners, function(p) {
				o.firing = h;
				if (p.fireFn.apply(p.scope || o.obj || window, n) === j) {
					return m = o.firing = j
				}
			});
			o.firing = j;
			return m
		}
	}
})();
Ext.apply(Ext.util.Observable.prototype, function() {
	function a(j) {
		var i = (this.methodEvents = this.methodEvents || {})[j], d, c, g, h = this;
		if (!i) {
			this.methodEvents[j] = i = {};
			i.originalFn = this[j];
			i.methodName = j;
			i.before = [];
			i.after = [];
			var b = function(l, k, e) {
				if (!Ext.isEmpty(c = l.apply(k || h, e))) {
					if (Ext.isObject(c)) {
						d = !Ext.isEmpty(c.returnValue) ? c.returnValue : c;
						g = !!c.cancel
					} else {
						if (c === false) {
							g = true
						} else {
							d = c
						}
					}
				}
			};
			this[j] = function() {
				var e = Ext.toArray(arguments);
				d = c = undefined;
				g = false;
				Ext.each(i.before, function(k) {
					b(k.fn, k.scope, e);
					if (g) {
						return d
					}
				});
				if (!Ext.isEmpty(c = i.originalFn.apply(h, e))) {
					d = c
				}
				Ext.each(i.after, function(k) {
					b(k.fn, k.scope, e);
					if (g) {
						return d
					}
				});
				return d
			}
		}
		return i
	}
	return {
		beforeMethod : function(d, c, b) {
			a.call(this, d).before.push({
				fn : c,
				scope : b
			})
		},
		afterMethod : function(d, c, b) {
			a.call(this, d).after.push({
				fn : c,
				scope : b
			})
		},
		removeMethodListener : function(h, c, b) {
			var g = a.call(this, h), d = false;
			Ext.each(g.before, function(j, k, e) {
				if (j.fn == c && j.scope == b) {
					e.splice(k, 1);
					d = true;
					return false
				}
			});
			if (!d) {
				Ext.each(g.after, function(j, k, e) {
					if (j.fn == c && j.scope == b) {
						e.splice(k, 1);
						return false
					}
				})
			}
		},
		relayEvents : function(e, b) {
			var d = this;
			function c(g) {
				return function() {
					return d.fireEvent.apply(d, [ g ].concat(Ext.toArray(arguments)))
				}
			}
			Ext.each(b, function(g) {
				d.events[g] = d.events[g] || true;
				e.on(g, c(g), d)
			})
		},
		enableBubble : function(b) {
			var c = this;
			b = Ext.isArray(b) ? b : Ext.toArray(arguments);
			Ext.each(b, function(d) {
				d = d.toLowerCase();
				var e = c.events[d] || true;
				if (typeof e == "boolean") {
					e = new Ext.util.Event(c, d);
					c.events[d] = e
				}
				e.bubble = true
			})
		}
	}
}());
Ext.util.Observable.capture = function(c, b, a) {
	c.fireEvent = c.fireEvent.createInterceptor(b, a)
};
Ext.util.Observable.observeClass = function(a) {
	Ext.apply(a, new Ext.util.Observable());
	a.prototype.fireEvent = function() {
		return (a.fireEvent.apply(a, arguments) !== false)
				&& (Ext.util.Observable.prototype.fireEvent.apply(this, arguments) !== false)
	}
};
Ext.EventManager = function() {
	var t, m, j = false, l = Ext.lib.Event, n = Ext.lib.Dom, b = document, u = window, g = "ie-deferred-loader", o = "DOMContentLoaded", e = {}, h = /^(?:scope|delay|buffer|single|stopEvent|preventDefault|stopPropagation|normalized|args|delegate)$/;
	function k(A, w, z, y, x) {
		var C = Ext.id(A), B = e[C] = e[C] || {};
		(B[w] = B[w] || []).push([ z, y, x ]);
		l.on(A, w, y);
		if (w == "mousewheel" && A.addEventListener) {
			var v = [ "DOMMouseScroll", y, false ];
			A.addEventListener.apply(A, v);
			l.on(window, "unload", function() {
				A.removeEventListener.apply(A, v)
			})
		}
		if (w == "mousedown" && A == document) {
			Ext.EventManager.stoppedMouseDownEvent.addListener(y)
		}
	}
	function c() {
		if (!j) {
			Ext.isReady = j = true;
			if (m) {
				clearInterval(m)
			}
			if (Ext.isGecko || Ext.isOpera) {
				b.removeEventListener(o, c, false)
			}
			if (Ext.isIE) {
				var v = b.getElementById(g);
				if (v) {
					v.onreadystatechange = null;
					v.parentNode.removeChild(v)
				}
			}
			if (t) {
				t.fire();
				t.clearListeners()
			}
		}
	}
	function a() {
		var v = "complete";
		t = new Ext.util.Event();
		if (Ext.isGecko || Ext.isOpera) {
			b.addEventListener(o, c, false)
		} else {
			if (Ext.isIE) {
				b.write("<script id=" + g + ' defer="defer" src="//:"><\/script>');
				b.getElementById(g).onreadystatechange = function() {
					if (this.readyState == v) {
						c()
					}
				}
			} else {
				if (Ext.isWebKit) {
					m = setInterval(function() {
						if (b.readyState == v) {
							c()
						}
					}, 10)
				}
			}
		}
		l.on(u, "load", c)
	}
	function r(v, w) {
		return function() {
			var x = Ext.toArray(arguments);
			if (w.target == Ext.EventObject.setEvent(x[0]).target) {
				v.apply(this, x)
			}
		}
	}
	function s(w, x) {
		var v = new Ext.util.DelayedTask(w);
		return function(y) {
			v.delay(x.buffer, w, null, [ new Ext.EventObjectImpl(y) ])
		}
	}
	function p(z, y, v, x, w) {
		return function(A) {
			Ext.EventManager.removeListener(y, v, x, w);
			z(A)
		}
	}
	function d(v, w) {
		return function(x) {
			x = new Ext.EventObjectImpl(x);
			setTimeout(function() {
				v(x)
			}, w.delay || 10)
		}
	}
	function i(x, w, v, B, A) {
		var C = !Ext.isObject(v) ? {} : v, z = Ext.getDom(x);
		B = B || C.fn;
		A = A || C.scope;
		if (!z) {
			throw 'Error listening for "' + w + '". Element "' + x + "\" doesn't exist."
		}
		function y(E) {
			if (!Ext) {
				return
			}
			E = Ext.EventObject.setEvent(E);
			var D;
			if (C.delegate) {
				if (!(D = E.getTarget(C.delegate, z))) {
					return
				}
			} else {
				D = E.target
			}
			if (C.stopEvent) {
				E.stopEvent()
			}
			if (C.preventDefault) {
				E.preventDefault()
			}
			if (C.stopPropagation) {
				E.stopPropagation()
			}
			if (C.normalized) {
				E = E.browserEvent
			}
			B.call(A || z, E, D, C)
		}
		if (C.target) {
			y = r(y, C)
		}
		if (C.delay) {
			y = d(y, C)
		}
		if (C.single) {
			y = p(y, z, w, B, A)
		}
		if (C.buffer) {
			y = s(y, C)
		}
		k(z, w, B, y, A);
		return y
	}
	var q = {
		addListener : function(x, v, z, y, w) {
			if (Ext.isObject(v)) {
				var C = v, A, B;
				for (A in C) {
					B = C[A];
					if (!h.test(A)) {
						if (Ext.isFunction(B)) {
							i(x, A, C, B, C.scope)
						} else {
							i(x, A, B)
						}
					}
				}
			} else {
				i(x, v, w, z, y)
			}
		},
		removeListener : function(w, v, A, z) {
			var y = Ext.getDom(w), B = Ext.id(y), x;
			Ext.each((e[B] || {})[v], function(D, E, C) {
				if (Ext.isArray(D) && D[0] == A && (!z || D[2] == z)) {
					l.un(y, v, x = D[1]);
					C.splice(E, 1);
					return false
				}
			});
			if (v == "mousewheel" && y.addEventListener && x) {
				y.removeEventListener("DOMMouseScroll", x, false)
			}
			if (v == "mousedown" && y == b && x) {
				Ext.EventManager.stoppedMouseDownEvent.removeListener(x)
			}
		},
		removeAll : function(w) {
			var y = Ext.id(w = Ext.getDom(w)), x = e[y], v;
			for (v in x) {
				if (x.hasOwnProperty(v)) {
					Ext.each(x[v], function(z) {
						l.un(w, v, z.wrap)
					})
				}
			}
			e[y] = null
		},
		onDocumentReady : function(x, w, v) {
			if (j) {
				t.addListener(x, w, v);
				t.fire();
				t.clearListeners()
			} else {
				if (!t) {
					a()
				}
				v = v || {};
				v.delay = v.delay || 1;
				t.addListener(x, w, v)
			}
		},
		elHash : e
	};
	q.on = q.addListener;
	q.un = q.removeListener;
	q.stoppedMouseDownEvent = new Ext.util.Event();
	return q
}();
Ext.onReady = Ext.EventManager.onDocumentReady;
(function() {
	var a = function() {
		var c = document.body || document.getElementsByTagName("body")[0];
		if (!c) {
			return false
		}
		var b = [
				" ",
				Ext.isIE ? "ext-ie " + (Ext.isIE6 ? "ext-ie6" : (Ext.isIE7 ? "ext-ie7" : "ext-ie8"))
						: Ext.isGecko ? "ext-gecko " + (Ext.isGecko2 ? "ext-gecko2" : "ext-gecko3")
								: Ext.isOpera ? "ext-opera" : Ext.isWebKit ? "ext-webkit" : "" ];
		if (Ext.isSafari) {
			b.push("ext-safari " + (Ext.isSafari2 ? "ext-safari2" : (Ext.isSafari3 ? "ext-safari3" : "ext-safari4")))
		} else {
			if (Ext.isChrome) {
				b.push("ext-chrome")
			}
		}
		if (Ext.isMac) {
			b.push("ext-mac")
		}
		if (Ext.isLinux) {
			b.push("ext-linux")
		}
		if (Ext.isStrict || Ext.isBorderBox) {
			var d = c.parentNode;
			if (d) {
				d.className += Ext.isStrict ? " ext-strict" : " ext-border-box"
			}
		}
		c.className += b.join(" ");
		return true
	};
	if (!a()) {
		Ext.onReady(a)
	}
})();
Ext.EventObject = function() {
	var b = Ext.lib.Event, a = {
		3 : 13,
		63234 : 37,
		63235 : 39,
		63232 : 38,
		63233 : 40,
		63276 : 33,
		63277 : 34,
		63272 : 46,
		63273 : 36,
		63275 : 35
	}, c = Ext.isIE ? {
		1 : 0,
		4 : 1,
		2 : 2
	} : (Ext.isWebKit ? {
		1 : 0,
		2 : 1,
		3 : 2
	} : {
		0 : 0,
		1 : 1,
		2 : 2
	});
	Ext.EventObjectImpl = function(d) {
		if (d) {
			this.setEvent(d.browserEvent || d)
		}
	};
	Ext.EventObjectImpl.prototype = {
		setEvent : function(g) {
			var d = this;
			if (g == d || (g && g.browserEvent)) {
				return g
			}
			d.browserEvent = g;
			if (g) {
				d.button = g.button ? c[g.button] : (g.which ? g.which - 1 : -1);
				if (g.type == "click" && d.button == -1) {
					d.button = 0
				}
				d.type = g.type;
				d.shiftKey = g.shiftKey;
				d.ctrlKey = g.ctrlKey || g.metaKey || false;
				d.altKey = g.altKey;
				d.keyCode = g.keyCode;
				d.charCode = g.charCode;
				d.target = b.getTarget(g);
				d.xy = b.getXY(g)
			} else {
				d.button = -1;
				d.shiftKey = false;
				d.ctrlKey = false;
				d.altKey = false;
				d.keyCode = 0;
				d.charCode = 0;
				d.target = null;
				d.xy = [ 0, 0 ]
			}
			return d
		},
		stopEvent : function() {
			var d = this;
			if (d.browserEvent) {
				if (d.browserEvent.type == "mousedown") {
					Ext.EventManager.stoppedMouseDownEvent.fire(d)
				}
				b.stopEvent(d.browserEvent)
			}
		},
		preventDefault : function() {
			if (this.browserEvent) {
				b.preventDefault(this.browserEvent)
			}
		},
		stopPropagation : function() {
			var d = this;
			if (d.browserEvent) {
				if (d.browserEvent.type == "mousedown") {
					Ext.EventManager.stoppedMouseDownEvent.fire(d)
				}
				b.stopPropagation(d.browserEvent)
			}
		},
		getCharCode : function() {
			return this.charCode || this.keyCode
		},
		getKey : function() {
			return this.normalizeKey(this.keyCode || this.charCode)
		},
		normalizeKey : function(d) {
			return Ext.isSafari ? (a[d] || d) : d
		},
		getPageX : function() {
			return this.xy[0]
		},
		getPageY : function() {
			return this.xy[1]
		},
		getXY : function() {
			return this.xy
		},
		getTarget : function(e, g, d) {
			return e ? Ext.fly(this.target).findParent(e, g, d) : (d ? Ext.get(this.target) : this.target)
		},
		getRelatedTarget : function() {
			return this.browserEvent ? b.getRelatedTarget(this.browserEvent) : null
		},
		getWheelDelta : function() {
			var d = this.browserEvent;
			var g = 0;
			if (d.wheelDelta) {
				g = d.wheelDelta / 120
			} else {
				if (d.detail) {
					g = -d.detail / 3
				}
			}
			return g
		},
		within : function(g, h, d) {
			if (g) {
				var e = this[h ? "getRelatedTarget" : "getTarget"]();
				return e && ((d ? (e == Ext.getDom(g)) : false) || Ext.fly(g).contains(e))
			}
			return false
		}
	};
	return new Ext.EventObjectImpl()
}();
Ext
		.apply(
				Ext.EventManager,
				function() {
					var c, j, e, b, a = Ext.lib.Dom, k = Ext.lib.Event, i = /^(?:scope|delay|buffer|single|stopEvent|preventDefault|stopPropagation|normalized|args|delegate)$/, h = 0, g = 0, d = Ext.isSafari ? Ext
							.num(navigator.userAgent.toLowerCase().match(/version\/(\d+\.\d)/)[1] || 2) >= 3.1
							: !((Ext.isGecko && !Ext.isWindows) || Ext.isOpera);
					return {
						doResizeEvent : function() {
							var m = a.getViewHeight(), l = a.getViewWidth();
							if (g != m || h != l) {
								c.fire(h = l, g = m)
							}
						},
						onWindowResize : function(n, m, l) {
							if (!c) {
								c = new Ext.util.Event();
								j = new Ext.util.DelayedTask(this.doResizeEvent);
								k.on(window, "resize", this.fireWindowResize, this)
							}
							c.addListener(n, m, l)
						},
						fireWindowResize : function() {
							if (c) {
								if ((Ext.isIE || Ext.isAir) && j) {
									j.delay(50)
								} else {
									c.fire(a.getViewWidth(), a.getViewHeight())
								}
							}
						},
						onTextResize : function(o, n, l) {
							if (!e) {
								e = new Ext.util.Event();
								var m = new Ext.Element(document.createElement("div"));
								m.dom.className = "x-text-resize";
								m.dom.innerHTML = "X";
								m.appendTo(document.body);
								b = m.dom.offsetHeight;
								setInterval(function() {
									if (m.dom.offsetHeight != b) {
										e.fire(b, b = m.dom.offsetHeight)
									}
								}, this.textResizeInterval)
							}
							e.addListener(o, n, l)
						},
						removeResizeListener : function(m, l) {
							if (c) {
								c.removeListener(m, l)
							}
						},
						fireResize : function() {
							if (c) {
								c.fire(a.getViewWidth(), a.getViewHeight())
							}
						},
						textResizeInterval : 50,
						ieDeferSrc : false,
						useKeydown : d
					}
				}());
Ext.EventManager.on = Ext.EventManager.addListener;
Ext.apply(Ext.EventObjectImpl.prototype, {
	BACKSPACE : 8,
	TAB : 9,
	NUM_CENTER : 12,
	ENTER : 13,
	RETURN : 13,
	SHIFT : 16,
	CTRL : 17,
	CONTROL : 17,
	ALT : 18,
	PAUSE : 19,
	CAPS_LOCK : 20,
	ESC : 27,
	SPACE : 32,
	PAGE_UP : 33,
	PAGEUP : 33,
	PAGE_DOWN : 34,
	PAGEDOWN : 34,
	END : 35,
	HOME : 36,
	LEFT : 37,
	UP : 38,
	RIGHT : 39,
	DOWN : 40,
	PRINT_SCREEN : 44,
	INSERT : 45,
	DELETE : 46,
	ZERO : 48,
	ONE : 49,
	TWO : 50,
	THREE : 51,
	FOUR : 52,
	FIVE : 53,
	SIX : 54,
	SEVEN : 55,
	EIGHT : 56,
	NINE : 57,
	A : 65,
	B : 66,
	C : 67,
	D : 68,
	E : 69,
	F : 70,
	G : 71,
	H : 72,
	I : 73,
	J : 74,
	K : 75,
	L : 76,
	M : 77,
	N : 78,
	O : 79,
	P : 80,
	Q : 81,
	R : 82,
	S : 83,
	T : 84,
	U : 85,
	V : 86,
	W : 87,
	X : 88,
	Y : 89,
	Z : 90,
	CONTEXT_MENU : 93,
	NUM_ZERO : 96,
	NUM_ONE : 97,
	NUM_TWO : 98,
	NUM_THREE : 99,
	NUM_FOUR : 100,
	NUM_FIVE : 101,
	NUM_SIX : 102,
	NUM_SEVEN : 103,
	NUM_EIGHT : 104,
	NUM_NINE : 105,
	NUM_MULTIPLY : 106,
	NUM_PLUS : 107,
	NUM_MINUS : 109,
	NUM_PERIOD : 110,
	NUM_DIVISION : 111,
	F1 : 112,
	F2 : 113,
	F3 : 114,
	F4 : 115,
	F5 : 116,
	F6 : 117,
	F7 : 118,
	F8 : 119,
	F9 : 120,
	F10 : 121,
	F11 : 122,
	F12 : 123,
	isNavKeyPress : function() {
		var b = this, a = this.normalizeKey(b.keyCode);
		return (a >= 33 && a <= 40) || a == b.RETURN || a == b.TAB || a == b.ESC
	},
	isSpecialKey : function() {
		var a = this.normalizeKey(this.keyCode);
		return (this.type == "keypress" && this.ctrlKey) || this.isNavKeyPress() || (a == this.BACKSPACE)
				|| (a >= 16 && a <= 20) || (a >= 44 && a <= 45)
	},
	getPoint : function() {
		return new Ext.lib.Point(this.xy[0], this.xy[1])
	},
	hasModifier : function() {
		return ((this.ctrlKey || this.altKey) || this.shiftKey)
	}
});
(function() {
	var i = document;
	Ext.Element = function(n, o) {
		var p = typeof n == "string" ? i.getElementById(n) : n, q;
		if (!p) {
			return null
		}
		q = p.id;
		if (!o && q && Ext.Element.cache[q]) {
			return Ext.Element.cache[q]
		}
		this.dom = p;
		this.id = q || Ext.id(p)
	};
	var a = Ext.lib.Dom, e = Ext.DomHelper, l = Ext.lib.Event, d = Ext.lib.Anim, g = Ext.Element;
	g.prototype = {
		set : function(s, p) {
			var q = this.dom, n, r;
			for (n in s) {
				r = s[n];
				if (n != "style" && !Ext.isFunction(r)) {
					if (n == "cls") {
						q.className = r
					} else {
						if (s.hasOwnProperty(n)) {
							if (p || !!q.setAttribute) {
								q.setAttribute(n, r)
							} else {
								q[n] = r
							}
						}
					}
				}
			}
			if (s.style) {
				Ext.DomHelper.applyStyles(q, s.style)
			}
			return this
		},
		defaultUnit : "px",
		is : function(n) {
			return Ext.DomQuery.is(this.dom, n)
		},
		focus : function(q, p) {
			var n = this, p = p || n.dom;
			try {
				if (Number(q)) {
					n.focus.defer(q, null, [ null, p ])
				} else {
					p.focus()
				}
			} catch (o) {
			}
			return n
		},
		blur : function() {
			try {
				this.dom.blur()
			} catch (n) {
			}
			return this
		},
		getValue : function(n) {
			var o = this.dom.value;
			return n ? parseInt(o, 10) : o
		},
		addListener : function(n, q, p, o) {
			Ext.EventManager.on(this.dom, n, q, p || this, o);
			return this
		},
		removeListener : function(n, p, o) {
			Ext.EventManager.removeListener(this.dom, n, p, o || this);
			return this
		},
		removeAllListeners : function() {
			Ext.EventManager.removeAll(this.dom);
			return this
		},
		addUnits : function(n) {
			if (n === "" || n == "auto" || n === undefined) {
				n = n || ""
			} else {
				if (!isNaN(n) || !j.test(n)) {
					n = n + (this.defaultUnit || "px")
				}
			}
			return n
		},
		load : function(o, p, n) {
			Ext.Ajax.request(Ext.apply({
				params : p,
				url : o.url || o,
				callback : n,
				el : this.dom,
				indicatorText : o.indicatorText || ""
			}, Ext.isObject(o) ? o : {}));
			return this
		},
		isBorderBox : function() {
			return h[(this.dom.tagName || "").toLowerCase()] || Ext.isBorderBox
		},
		remove : function() {
			var n = this, o = n.dom;
			n.removeAllListeners();
			delete g.cache[o.id];
			delete g.dataCache[o.id];
			Ext.removeNode(o)
		},
		hover : function(o, n, q, p) {
			var r = this;
			r.on("mouseenter", o, q || r.dom, p);
			r.on("mouseleave", n, q || r.dom, p);
			return r
		},
		contains : function(n) {
			return !n ? false : Ext.lib.Dom.isAncestor(this.dom, n.dom ? n.dom : n)
		},
		getAttributeNS : function(o, n) {
			return this.getAttribute(n, o)
		},
		getAttribute : Ext.isIE ? function(n, p) {
			var q = this.dom, o = typeof q[p + ":" + n];
			if ([ "undefined", "unknown" ].indexOf(o) == -1) {
				return q[p + ":" + n]
			}
			return q[n]
		} : function(n, o) {
			var p = this.dom;
			return p.getAttributeNS(o, n) || p.getAttribute(o + ":" + n) || p.getAttribute(n) || p[n]
		},
		update : function(n) {
			this.dom.innerHTML = n;
			return this
		}
	};
	var m = g.prototype;
	g.addMethods = function(n) {
		Ext.apply(m, n)
	};
	m.on = m.addListener;
	m.un = m.removeListener;
	m.autoBoxAdjust = true;
	var j = /\d+(px|em|%|en|ex|pt|in|cm|mm|pc)$/i, c;
	g.cache = {};
	g.dataCache = {};
	g.get = function(o) {
		var n, r, q;
		if (!o) {
			return null
		}
		if (typeof o == "string") {
			if (!(r = i.getElementById(o))) {
				return null
			}
			if (n = g.cache[o]) {
				n.dom = r
			} else {
				n = g.cache[o] = new g(r)
			}
			return n
		} else {
			if (o.tagName) {
				if (!(q = o.id)) {
					q = Ext.id(o)
				}
				if (n = g.cache[q]) {
					n.dom = o
				} else {
					n = g.cache[q] = new g(o)
				}
				return n
			} else {
				if (o instanceof g) {
					if (o != c) {
						o.dom = i.getElementById(o.id) || o.dom;
						g.cache[o.id] = o
					}
					return o
				} else {
					if (o.isComposite) {
						return o
					} else {
						if (Ext.isArray(o)) {
							return g.select(o)
						} else {
							if (o == i) {
								if (!c) {
									var p = function() {
									};
									p.prototype = g.prototype;
									c = new p();
									c.dom = i
								}
								return c
							}
						}
					}
				}
			}
		}
		return null
	};
	g.data = function(o, n, p) {
		var q = g.dataCache[o.id];
		if (!q) {
			q = g.dataCache[o.id] = {}
		}
		if (arguments.length == 2) {
			return q[n]
		} else {
			q[n] = p
		}
	};
	function k() {
		if (!Ext.enableGarbageCollector) {
			clearInterval(g.collectorThread)
		} else {
			var n, o, p;
			for (n in g.cache) {
				o = g.cache[n];
				p = o.dom;
				if (!p || !p.parentNode || (!p.offsetParent && !i.getElementById(n))) {
					delete g.cache[n];
					if (p && Ext.enableListenerCollection) {
						Ext.EventManager.removeAll(p)
					}
				}
			}
		}
	}
	g.collectorThreadId = setInterval(k, 30000);
	var b = function() {
	};
	b.prototype = g.prototype;
	g.Flyweight = function(n) {
		this.dom = n
	};
	g.Flyweight.prototype = new b();
	g.Flyweight.prototype.isFlyweight = true;
	g._flyweights = {};
	g.fly = function(p, n) {
		var o = null;
		n = n || "_global";
		if (p = Ext.getDom(p)) {
			(g._flyweights[n] = g._flyweights[n] || new g.Flyweight()).dom = p;
			o = g._flyweights[n]
		}
		return o
	};
	Ext.get = g.get;
	Ext.fly = g.fly;
	var h = Ext.isStrict ? {
		select : 1
	} : {
		input : 1,
		select : 1,
		textarea : 1
	};
	if (Ext.isIE || Ext.isGecko) {
		h.button = 1
	}
	Ext.EventManager.on(window, "unload", function() {
		delete g.cache;
		delete g.dataCache;
		delete g._flyweights
	})
})();
Ext.Element
		.addMethods({
			swallowEvent : function(a, b) {
				var d = this;
				function c(g) {
					g.stopPropagation();
					if (b) {
						g.preventDefault()
					}
				}
				if (Ext.isArray(a)) {
					Ext.each(a, function(g) {
						d.on(g, c)
					});
					return d
				}
				d.on(a, c);
				return d
			},
			relayEvent : function(a, b) {
				this.on(a, function(c) {
					b.fireEvent(a, c)
				})
			},
			clean : function(b) {
				var d = this, e = d.dom, g = e.firstChild, c = -1;
				if (Ext.Element.data(e, "isCleaned") && b !== true) {
					return d
				}
				while (g) {
					var a = g.nextSibling;
					if (g.nodeType == 3 && !/\S/.test(g.nodeValue)) {
						e.removeChild(g)
					} else {
						g.nodeIndex = ++c
					}
					g = a
				}
				Ext.Element.data(e, "isCleaned", true);
				return d
			},
			load : function() {
				var a = this.getUpdater();
				a.update.apply(a, arguments);
				return this
			},
			getUpdater : function() {
				return this.updateManager || (this.updateManager = new Ext.Updater(this))
			},
			update : function(html, loadScripts, callback) {
				html = html || "";
				if (loadScripts !== true) {
					this.dom.innerHTML = html;
					if (Ext.isFunction(callback)) {
						callback()
					}
					return this
				}
				var id = Ext.id(), dom = this.dom;
				html += '<span id="' + id + '"></span>';
				Ext.lib.Event
						.onAvailable(
								id,
								function() {
									var DOC = document, hd = DOC.getElementsByTagName("head")[0], re = /(?:<script([^>]*)?>)((\n|\r|.)*?)(?:<\/script>)/ig, srcRe = /\ssrc=([\'\"])(.*?)\1/i, typeRe = /\stype=([\'\"])(.*?)\1/i, match, attrs, srcMatch, typeMatch, el, s;
									while ((match = re.exec(html))) {
										attrs = match[1];
										srcMatch = attrs ? attrs.match(srcRe) : false;
										if (srcMatch && srcMatch[2]) {
											s = DOC.createElement("script");
											s.src = srcMatch[2];
											typeMatch = attrs.match(typeRe);
											if (typeMatch && typeMatch[2]) {
												s.type = typeMatch[2]
											}
											hd.appendChild(s)
										} else {
											if (match[2] && match[2].length > 0) {
												if (window.execScript) {
													window.execScript(match[2])
												} else {
													window.eval(match[2])
												}
											}
										}
									}
									el = DOC.getElementById(id);
									if (el) {
										Ext.removeNode(el)
									}
									if (Ext.isFunction(callback)) {
										callback()
									}
								});
				dom.innerHTML = html.replace(/(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)/ig, "");
				return this
			},
			createProxy : function(a, e, d) {
				a = Ext.isObject(a) ? a : {
					tag : "div",
					cls : a
				};
				var c = this, b = e ? Ext.DomHelper.append(e, a, true) : Ext.DomHelper.insertBefore(c.dom, a, true);
				if (d && c.setBox && c.getBox) {
					b.setBox(c.getBox())
				}
				return b
			}
		});
Ext.Element.prototype.getUpdateManager = Ext.Element.prototype.getUpdater;
Ext.Element.uncache = function(e) {
	for ( var d = 0, c = arguments, b = c.length; d < b; d++) {
		if (c[d]) {
			delete Ext.Element.cache[c[d].id || c[d]]
		}
	}
};
Ext.Element
		.addMethods({
			getAnchorXY : function(e, l, q) {
				e = (e || "tl").toLowerCase();
				q = q || {};
				var k = this, b = k.dom == document.body || k.dom == document, n = q.width || b ? Ext.lib.Dom
						.getViewWidth() : k.getWidth(), i = q.height || b ? Ext.lib.Dom.getViewHeight() : k.getHeight(), p, a = Math.round, c = k
						.getXY(), m = k.getScroll(), j = b ? m.left : !l ? c[0] : 0, g = b ? m.top : !l ? c[1] : 0, d = {
					c : [ a(n * 0.5), a(i * 0.5) ],
					t : [ a(n * 0.5), 0 ],
					l : [ 0, a(i * 0.5) ],
					r : [ n, a(i * 0.5) ],
					b : [ a(n * 0.5), i ],
					tl : [ 0, 0 ],
					bl : [ 0, i ],
					br : [ n, i ],
					tr : [ n, 0 ]
				};
				p = d[e];
				return [ p[0] + j, p[1] + g ]
			},
			anchorTo : function(b, g, c, a, i, j) {
				var h = this, e = h.dom;
				function d() {
					Ext.fly(e).alignTo(b, g, c, a);
					Ext.callback(j, Ext.fly(e))
				}
				Ext.EventManager.onWindowResize(d, h);
				if (!Ext.isEmpty(i)) {
					Ext.EventManager.on(window, "scroll", d, h, {
						buffer : !isNaN(i) ? i : 50
					})
				}
				d.call(h);
				return h
			},
			getAlignToXY : function(g, A, B) {
				g = Ext.get(g);
				if (!g || !g.dom) {
					throw "Element.alignToXY with an element that doesn't exist"
				}
				B = B || [ 0, 0 ];
				A = (A == "?" ? "tl-bl?" : (!/-/.test(A) && A !== "" ? "tl-" + A : A || "tl-bl")).toLowerCase();
				var K = this, H = K.dom, M, L, n, l, s, F, v, t = Ext.lib.Dom.getViewWidth() - 10, G = Ext.lib.Dom
						.getViewHeight() - 10, b, i, j, k, u, z, N = document, J = N.documentElement, q = N.body, E = (J.scrollLeft
						|| q.scrollLeft || 0) + 5, D = (J.scrollTop || q.scrollTop || 0) + 5, I = false, e = "", a = "", C = A
						.match(/^([a-z]+)-([a-z]+)(\?)?$/);
				if (!C) {
					throw "Element.alignTo with an invalid alignment " + A
				}
				e = C[1];
				a = C[2];
				I = !!C[3];
				M = K.getAnchorXY(e, true);
				L = g.getAnchorXY(a, false);
				n = L[0] - M[0] + B[0];
				l = L[1] - M[1] + B[1];
				if (I) {
					s = K.getWidth();
					F = K.getHeight();
					v = g.getRegion();
					b = e.charAt(0);
					i = e.charAt(e.length - 1);
					j = a.charAt(0);
					k = a.charAt(a.length - 1);
					u = ((b == "t" && j == "b") || (b == "b" && j == "t"));
					z = ((i == "r" && k == "l") || (i == "l" && k == "r"));
					if (n + s > t + E) {
						n = z ? v.left - s : t + E - s
					}
					if (n < E) {
						n = z ? v.right : E
					}
					if (l + F > G + D) {
						l = u ? v.top - F : G + D - F
					}
					if (l < D) {
						l = u ? v.bottom : D
					}
				}
				return [ n, l ]
			},
			alignTo : function(c, a, e, b) {
				var d = this;
				return d.setXY(d.getAlignToXY(c, a, e), d.preanim && !!b ? d.preanim(arguments, 3) : false)
			},
			adjustForConstraints : function(c, a, b) {
				return this.getConstrainToXY(a || document, false, b, c) || c
			},
			getConstrainToXY : function(b, a, c, e) {
				var d = {
					top : 0,
					left : 0,
					bottom : 0,
					right : 0
				};
				return function(i, z, k, m) {
					i = Ext.get(i);
					k = k ? Ext.applyIf(k, d) : d;
					var v, C, u = 0, t = 0;
					if (i.dom == document.body || i.dom == document) {
						v = Ext.lib.Dom.getViewWidth();
						C = Ext.lib.Dom.getViewHeight()
					} else {
						v = i.dom.clientWidth;
						C = i.dom.clientHeight;
						if (!z) {
							var r = i.getXY();
							u = r[0];
							t = r[1]
						}
					}
					var q = i.getScroll();
					u += k.left + q.left;
					t += k.top + q.top;
					v -= k.right;
					C -= k.bottom;
					var A = u + v;
					var g = t + C;
					var j = m || (!z ? this.getXY() : [ this.getLeft(true), this.getTop(true) ]);
					var o = j[0], n = j[1];
					var p = this.dom.offsetWidth, B = this.dom.offsetHeight;
					var l = false;
					if ((o + p) > A) {
						o = A - p;
						l = true
					}
					if ((n + B) > g) {
						n = g - B;
						l = true
					}
					if (o < u) {
						o = u;
						l = true
					}
					if (n < t) {
						n = t;
						l = true
					}
					return l ? [ o, n ] : false
				}
			}(),
			getCenterXY : function() {
				return this.getAlignToXY(document, "c-c")
			},
			center : function(a) {
				return this.alignTo(a || document, "c-c")
			}
		});
Ext.Element.addMethods(function() {
	var d = "parentNode", b = "nextSibling", c = "previousSibling", e = Ext.DomQuery, a = Ext.get;
	return {
		findParent : function(m, l, h) {
			var j = this.dom, g = document.body, k = 0, i;
			if (Ext.isGecko && Object.prototype.toString.call(j) == "[object XULElement]") {
				return null
			}
			l = l || 50;
			if (isNaN(l)) {
				i = Ext.getDom(l);
				l = Number.MAX_VALUE
			}
			while (j && j.nodeType == 1 && k < l && j != g && j != i) {
				if (e.is(j, m)) {
					return h ? a(j) : j
				}
				k++;
				j = j.parentNode
			}
			return null
		},
		findParentNode : function(j, i, g) {
			var h = Ext.fly(this.dom.parentNode, "_internal");
			return h ? h.findParent(j, i, g) : null
		},
		up : function(h, g) {
			return this.findParentNode(h, g, true)
		},
		select : function(g, h) {
			return Ext.Element.select(g, h, this.dom)
		},
		query : function(g, h) {
			return e.select(g, this.dom)
		},
		child : function(g, h) {
			var i = e.selectNode(g, this.dom);
			return h ? i : a(i)
		},
		down : function(g, h) {
			var i = e.selectNode(" > " + g, this.dom);
			return h ? i : a(i)
		},
		parent : function(g, h) {
			return this.matchNode(d, d, g, h)
		},
		next : function(g, h) {
			return this.matchNode(b, b, g, h)
		},
		prev : function(g, h) {
			return this.matchNode(c, c, g, h)
		},
		first : function(g, h) {
			return this.matchNode(b, "firstChild", g, h)
		},
		last : function(g, h) {
			return this.matchNode(c, "lastChild", g, h)
		},
		matchNode : function(h, k, g, i) {
			var j = this.dom[k];
			while (j) {
				if (j.nodeType == 1 && (!g || e.is(j, g))) {
					return !i ? a(j) : j
				}
				j = j[h]
			}
			return null
		}
	}
}());
Ext.Element.addMethods(function() {
	var d = Ext.getDom, a = Ext.get, c = Ext.DomHelper, b = function(e) {
		return (e.nodeType || e.dom || typeof e == "string")
	};
	return {
		appendChild : function(e) {
			return a(e).appendTo(this)
		},
		appendTo : function(e) {
			d(e).appendChild(this.dom);
			return this
		},
		insertBefore : function(e) {
			(e = d(e)).parentNode.insertBefore(this.dom, e);
			return this
		},
		insertAfter : function(e) {
			(e = d(e)).parentNode.insertBefore(this.dom, e.nextSibling);
			return this
		},
		insertFirst : function(g, e) {
			g = g || {};
			if (b(g)) {
				g = d(g);
				this.dom.insertBefore(g, this.dom.firstChild);
				return !e ? a(g) : g
			} else {
				return this.createChild(g, this.dom.firstChild, e)
			}
		},
		replace : function(e) {
			e = a(e);
			this.insertBefore(e);
			e.remove();
			return this
		},
		replaceWith : function(g) {
			var h = this, e = Ext.Element;
			if (b(g)) {
				g = d(g);
				h.dom.parentNode.insertBefore(g, h.dom)
			} else {
				g = c.insertBefore(h.dom, g)
			}
			delete e.cache[h.id];
			Ext.removeNode(h.dom);
			h.id = Ext.id(h.dom = g);
			return e.cache[h.id] = h
		},
		createChild : function(g, e, h) {
			g = g || {
				tag : "div"
			};
			return e ? c.insertBefore(e, g, h !== true) : c[!this.dom.firstChild ? "overwrite" : "append"](this.dom, g,
					h !== true)
		},
		wrap : function(e, g) {
			var h = c.insertBefore(this.dom, e || {
				tag : "div"
			}, !g);
			h.dom ? h.dom.appendChild(this.dom) : h.appendChild(this.dom);
			return h
		},
		insertHtml : function(g, h, e) {
			var i = c.insertHtml(g, this.dom, h);
			return e ? Ext.get(i) : i
		}
	}
}());
Ext.apply(Ext.Element.prototype, function() {
	var c = Ext.getDom, a = Ext.get, b = Ext.DomHelper;
	return {
		insertSibling : function(h, e, g) {
			var i = this, d;
			if (Ext.isArray(h)) {
				Ext.each(h, function(j) {
					d = i.insertSibling(j, e, g)
				});
				return d
			}
			e = (e || "before").toLowerCase();
			h = h || {};
			if (h.nodeType || h.dom) {
				d = i.dom.parentNode.insertBefore(c(h), e == "before" ? i.dom : i.dom.nextSibling);
				if (!g) {
					d = a(d)
				}
			} else {
				if (e == "after" && !i.dom.nextSibling) {
					d = b.append(i.dom.parentNode, h, !g)
				} else {
					d = b[e == "after" ? "insertAfter" : "insertBefore"](i.dom, h, !g)
				}
			}
			return d
		}
	}
}());
Ext.Element
		.addMethods(function() {
			var h = {}, y = /(-[a-z])/gi, b = {}, t = document.defaultView, v = Ext.isIE ? "styleFloat" : "cssFloat", D = /alpha\(opacity=(.*)\)/i, l = /^\s+|\s+$/g, B = Ext.Element, d = "padding", c = "margin", z = "border", u = "-left", r = "-right", x = "-top", o = "-bottom", j = "-width", s = Math, A = "hidden", e = "isClipped", k = "overflow", n = "overflow-x", m = "overflow-y", C = "originalClip", i = {
				l : z + u + j,
				r : z + r + j,
				t : z + x + j,
				b : z + o + j
			}, g = {
				l : d + u,
				r : d + r,
				t : d + x,
				b : d + o
			}, a = {
				l : c + u,
				r : c + r,
				t : c + x,
				b : c + o
			}, E = Ext.Element.data;
			function q(F, G) {
				return G.charAt(1).toUpperCase()
			}
			function p(G, F) {
				var H = 0;
				Ext.each(G.match(/\w/g), function(I) {
					if (I = parseInt(this.getStyle(F[I]), 10)) {
						H += s.abs(I)
					}
				}, this);
				return H
			}
			function w(F) {
				return h[F] || (h[F] = F == "float" ? v : F.replace(y, q))
			}
			return {
				adjustWidth : function(F) {
					var G = this;
					var H = (typeof F == "number");
					if (H && G.autoBoxAdjust && !G.isBorderBox()) {
						F -= (G.getBorderWidth("lr") + G.getPadding("lr"))
					}
					return (H && F < 0) ? 0 : F
				},
				adjustHeight : function(F) {
					var G = this;
					var H = (typeof F == "number");
					if (H && G.autoBoxAdjust && !G.isBorderBox()) {
						F -= (G.getBorderWidth("tb") + G.getPadding("tb"))
					}
					return (H && F < 0) ? 0 : F
				},
				addClass : function(F) {
					var G = this;
					Ext.each(F, function(H) {
						G.dom.className += (!G.hasClass(H) && H ? " " + H : "")
					});
					return G
				},
				radioClass : function(F) {
					Ext.each(this.dom.parentNode.childNodes, function(G) {
						if (G.nodeType == 1) {
							Ext.fly(G, "_internal").removeClass(F)
						}
					});
					return this.addClass(F)
				},
				removeClass : function(F) {
					var G = this;
					if (G.dom.className) {
						Ext.each(F, function(H) {
							G.dom.className = G.dom.className.replace(b[H] = b[H]
									|| new RegExp("(?:^|\\s+)" + H + "(?:\\s+|$)", "g"), " ")
						})
					}
					return G
				},
				toggleClass : function(F) {
					return this.hasClass(F) ? this.removeClass(F) : this.addClass(F)
				},
				hasClass : function(F) {
					return F && (" " + this.dom.className + " ").indexOf(" " + F + " ") != -1
				},
				replaceClass : function(G, F) {
					return this.removeClass(G).addClass(F)
				},
				isStyle : function(F, G) {
					return this.getStyle(F) == G
				},
				getStyle : function() {
					return t && t.getComputedStyle ? function(I) {
						var H = this.dom, F, G;
						if (H == document) {
							return null
						}
						I = w(I);
						return (F = H.style[I]) ? F : (G = t.getComputedStyle(H, "")) ? G[I] : null
					} : function(J) {
						var H = this.dom, F, G;
						if (H == document) {
							return null
						}
						if (J == "opacity") {
							if (H.style.filter.match) {
								if (F = H.style.filter.match(D)) {
									var I = parseFloat(F[1]);
									if (!isNaN(I)) {
										return I ? I / 100 : 0
									}
								}
							}
							return 1
						}
						J = w(J);
						return H.style[J] || ((G = H.currentStyle) ? G[J] : null)
					}
				}(),
				getColor : function(F, G, K) {
					var I = this.getStyle(F), H = K || "#", J;
					if (!I || /transparent|inherit/.test(I)) {
						return G
					}
					if (/^r/.test(I)) {
						Ext.each(I.slice(4, I.length - 1).split(","), function(L) {
							J = parseInt(L, 10);
							H += (J < 16 ? "0" : "") + J.toString(16)
						})
					} else {
						I = I.replace("#", "");
						H += I.length == 3 ? I.replace(/^(\w)(\w)(\w)$/, "$1$1$2$2$3$3") : I
					}
					return (H.length > 5 ? H.toLowerCase() : G)
				},
				setStyle : function(J, I) {
					var G, H, F;
					if (!Ext.isObject(J)) {
						G = {};
						G[J] = I;
						J = G
					}
					for (H in J) {
						I = J[H];
						H == "opacity" ? this.setOpacity(I) : this.dom.style[w(H)] = I
					}
					return this
				},
				setOpacity : function(G, F) {
					var J = this, H = J.dom.style;
					if (!F || !J.anim) {
						if (Ext.isIE) {
							var I = G < 1 ? "alpha(opacity=" + G * 100 + ")" : "", K = H.filter.replace(D, "").replace(
									l, "");
							H.zoom = 1;
							H.filter = K + (K.length > 0 ? " " : "") + I
						} else {
							H.opacity = G
						}
					} else {
						J.anim({
							opacity : {
								to : G
							}
						}, J.preanim(arguments, 1), null, 0.35, "easeIn")
					}
					return J
				},
				clearOpacity : function() {
					var F = this.dom.style;
					if (Ext.isIE) {
						if (!Ext.isEmpty(F.filter)) {
							F.filter = F.filter.replace(D, "").replace(l, "")
						}
					} else {
						F.opacity = F["-moz-opacity"] = F["-khtml-opacity"] = ""
					}
					return this
				},
				getHeight : function(H) {
					var G = this, I = G.dom, F = s.max(I.offsetHeight, I.clientHeight) || 0;
					F = !H ? F : F - G.getBorderWidth("tb") - G.getPadding("tb");
					return F < 0 ? 0 : F
				},
				getWidth : function(G) {
					var H = this, I = H.dom, F = s.max(I.offsetWidth, I.clientWidth) || 0;
					F = !G ? F : F - H.getBorderWidth("lr") - H.getPadding("lr");
					return F < 0 ? 0 : F
				},
				setWidth : function(G, F) {
					var H = this;
					G = H.adjustWidth(G);
					!F || !H.anim ? H.dom.style.width = H.addUnits(G) : H.anim({
						width : {
							to : G
						}
					}, H.preanim(arguments, 1));
					return H
				},
				setHeight : function(F, G) {
					var H = this;
					F = H.adjustHeight(F);
					!G || !H.anim ? H.dom.style.height = H.addUnits(F) : H.anim({
						height : {
							to : F
						}
					}, H.preanim(arguments, 1));
					return H
				},
				getBorderWidth : function(F) {
					return p.call(this, F, i)
				},
				getPadding : function(F) {
					return p.call(this, F, g)
				},
				clip : function() {
					var F = this, G = F.dom;
					if (!E(G, e)) {
						E(G, e, true);
						E(G, C, {
							o : F.getStyle(k),
							x : F.getStyle(n),
							y : F.getStyle(m)
						});
						F.setStyle(k, A);
						F.setStyle(n, A);
						F.setStyle(m, A)
					}
					return F
				},
				unclip : function() {
					var F = this, H = F.dom;
					if (E(H, e)) {
						E(H, e, false);
						var G = E(H, C);
						if (G.o) {
							F.setStyle(k, G.o)
						}
						if (G.x) {
							F.setStyle(n, G.x)
						}
						if (G.y) {
							F.setStyle(m, G.y)
						}
					}
					return F
				},
				addStyles : p,
				margins : a
			}
		}());
Ext.Element.boxMarkup = '<div class="{0}-tl"><div class="{0}-tr"><div class="{0}-tc"></div></div></div><div class="{0}-ml"><div class="{0}-mr"><div class="{0}-mc"></div></div></div><div class="{0}-bl"><div class="{0}-br"><div class="{0}-bc"></div></div></div>';
Ext.Element.addMethods(function() {
	var a = "_internal";
	return {
		applyStyles : function(b) {
			Ext.DomHelper.applyStyles(this.dom, b);
			return this
		},
		getStyles : function() {
			var b = {};
			Ext.each(arguments, function(c) {
				b[c] = this.getStyle(c)
			}, this);
			return b
		},
		getStyleSize : function() {
			var g = this, b, e, i = this.dom, c = i.style;
			if (c.width && c.width != "auto") {
				b = parseInt(c.width, 10);
				if (g.isBorderBox()) {
					b -= g.getFrameWidth("lr")
				}
			}
			if (c.height && c.height != "auto") {
				e = parseInt(c.height, 10);
				if (g.isBorderBox()) {
					e -= g.getFrameWidth("tb")
				}
			}
			return {
				width : b || g.getWidth(true),
				height : e || g.getHeight(true)
			}
		},
		setOverflow : function(b) {
			var c = this.dom;
			if (b == "auto" && Ext.isMac && Ext.isGecko2) {
				c.style.overflow = "hidden";
				(function() {
					c.style.overflow = "auto"
				}).defer(1)
			} else {
				c.style.overflow = b
			}
		},
		boxWrap : function(b) {
			b = b || "x-box";
			var c = Ext.get(this.insertHtml("beforeBegin", "<div class='" + b + "'>"
					+ String.format(Ext.Element.boxMarkup, b) + "</div>"));
			Ext.DomQuery.selectNode("." + b + "-mc", c.dom).appendChild(this.dom);
			return c
		},
		setSize : function(d, b, c) {
			var e = this;
			if (Ext.isObject(d)) {
				b = d.height;
				d = d.width
			}
			d = e.adjustWidth(d);
			b = e.adjustHeight(b);
			if (!c || !e.anim) {
				e.dom.style.width = e.addUnits(d);
				e.dom.style.height = e.addUnits(b)
			} else {
				e.anim({
					width : {
						to : d
					},
					height : {
						to : b
					}
				}, e.preanim(arguments, 2))
			}
			return e
		},
		getComputedHeight : function() {
			var c = this, b = Math.max(c.dom.offsetHeight, c.dom.clientHeight);
			if (!b) {
				b = parseInt(c.getStyle("height"), 10) || 0;
				if (!c.isBorderBox()) {
					b += c.getFrameWidth("tb")
				}
			}
			return b
		},
		getComputedWidth : function() {
			var b = Math.max(this.dom.offsetWidth, this.dom.clientWidth);
			if (!b) {
				b = parseInt(this.getStyle("width"), 10) || 0;
				if (!this.isBorderBox()) {
					b += this.getFrameWidth("lr")
				}
			}
			return b
		},
		getFrameWidth : function(c, b) {
			return b && this.isBorderBox() ? 0 : (this.getPadding(c) + this.getBorderWidth(c))
		},
		addClassOnOver : function(b) {
			this.hover(function() {
				Ext.fly(this, a).addClass(b)
			}, function() {
				Ext.fly(this, a).removeClass(b)
			});
			return this
		},
		addClassOnFocus : function(b) {
			this.on("focus", function() {
				Ext.fly(this, a).addClass(b)
			}, this.dom);
			this.on("blur", function() {
				Ext.fly(this, a).removeClass(b)
			}, this.dom);
			return this
		},
		addClassOnClick : function(b) {
			var c = this.dom;
			this.on("mousedown", function() {
				Ext.fly(c, a).addClass(b);
				var g = Ext.getDoc(), e = function() {
					Ext.fly(c, a).removeClass(b);
					g.removeListener("mouseup", e)
				};
				g.on("mouseup", e)
			});
			return this
		},
		getViewSize : function() {
			var e = document, g = this.dom, c = Ext.lib.Dom, b = (g == e || g == e.body);
			return {
				width : (b ? c.getViewWidth() : g.clientWidth),
				height : (b ? c.getViewHeight() : g.clientHeight)
			}
		},
		getSize : function(b) {
			return {
				width : this.getWidth(b),
				height : this.getHeight(b)
			}
		},
		repaint : function() {
			var b = this.dom;
			this.addClass("x-repaint");
			setTimeout(function() {
				Ext.fly(b).removeClass("x-repaint")
			}, 1);
			return this
		},
		unselectable : function() {
			this.dom.unselectable = "on";
			return this.swallowEvent("selectstart", true).applyStyles("-moz-user-select:none;-khtml-user-select:none;")
					.addClass("x-unselectable")
		},
		getMargins : function(c) {
			var d = this, b, e = {
				t : "top",
				l : "left",
				r : "right",
				b : "bottom"
			}, g = {};
			if (!c) {
				for (b in d.margins) {
					g[e[b]] = parseInt(d.getStyle(d.margins[b]), 10) || 0
				}
				return g
			} else {
				return d.addStyles.call(d, c, d.margins)
			}
		}
	}
}());
(function() {
	var a = Ext.lib.Dom, b = "left", g = "right", d = "top", i = "bottom", h = "position", c = "static", e = "relative", j = "auto", k = "z-index";
	function l(n, m, o) {
		return this.preanim && !!m ? this.preanim(n, o) : false
	}
	Ext.Element.addMethods({
		getX : function() {
			return a.getX(this.dom)
		},
		getY : function() {
			return a.getY(this.dom)
		},
		getXY : function() {
			return a.getXY(this.dom)
		},
		getOffsetsTo : function(m) {
			var p = this.getXY(), n = Ext.fly(m, "_internal").getXY();
			return [ p[0] - n[0], p[1] - n[1] ]
		},
		setX : function(m, n) {
			return this.setXY([ m, this.getY() ], l.call(this, arguments, n, 1))
		},
		setY : function(n, m) {
			return this.setXY([ this.getX(), n ], l.call(this, arguments, m, 1))
		},
		setLeft : function(m) {
			this.setStyle(b, this.addUnits(m));
			return this
		},
		setTop : function(m) {
			this.setStyle(d, this.addUnits(m));
			return this
		},
		setRight : function(m) {
			this.setStyle(g, this.addUnits(m));
			return this
		},
		setBottom : function(m) {
			this.setStyle(i, this.addUnits(m));
			return this
		},
		setXY : function(o, m) {
			var n = this;
			if (!m || !n.anim) {
				a.setXY(n.dom, o)
			} else {
				n.anim({
					points : {
						to : o
					}
				}, n.preanim(arguments, 1), "motion")
			}
			return n
		},
		setLocation : function(m, o, n) {
			return this.setXY([ m, o ], l.call(this, arguments, n, 2))
		},
		moveTo : function(m, o, n) {
			return this.setXY([ m, o ], l.call(this, arguments, n, 2))
		},
		getLeft : function(m) {
			return !m ? this.getX() : parseInt(this.getStyle(b), 10) || 0
		},
		getRight : function(m) {
			var n = this;
			return !m ? n.getX() + n.getWidth() : (n.getLeft(true) + n.getWidth()) || 0
		},
		getTop : function(m) {
			return !m ? this.getY() : parseInt(this.getStyle(d), 10) || 0
		},
		getBottom : function(m) {
			var n = this;
			return !m ? n.getY() + n.getHeight() : (n.getTop(true) + n.getHeight()) || 0
		},
		position : function(q, p, m, o) {
			var n = this;
			if (!q && n.isStyle(h, c)) {
				n.setStyle(h, e)
			} else {
				if (q) {
					n.setStyle(h, q)
				}
			}
			if (p) {
				n.setStyle(k, p)
			}
			if (m || o) {
				n.setXY([ m || false, o || false ])
			}
		},
		clearPositioning : function(m) {
			m = m || "";
			this.setStyle({
				left : m,
				right : m,
				top : m,
				bottom : m,
				"z-index" : "",
				position : c
			});
			return this
		},
		getPositioning : function() {
			var m = this.getStyle(b);
			var n = this.getStyle(d);
			return {
				position : this.getStyle(h),
				left : m,
				right : m ? "" : this.getStyle(g),
				top : n,
				bottom : n ? "" : this.getStyle(i),
				"z-index" : this.getStyle(k)
			}
		},
		setPositioning : function(m) {
			var o = this, n = o.dom.style;
			o.setStyle(m);
			if (m.right == j) {
				n.right = ""
			}
			if (m.bottom == j) {
				n.bottom = ""
			}
			return o
		},
		translatePoints : function(m, u) {
			u = isNaN(m[1]) ? u : m[1];
			m = isNaN(m[0]) ? m : m[0];
			var q = this, r = q.isStyle(h, e), s = q.getXY(), n = parseInt(q.getStyle(b), 10), p = parseInt(q
					.getStyle(d), 10);
			n = !isNaN(n) ? n : (r ? 0 : q.dom.offsetLeft);
			p = !isNaN(p) ? p : (r ? 0 : q.dom.offsetTop);
			return {
				left : (m - s[0] + n),
				top : (u - s[1] + p)
			}
		},
		animTest : l
	})
})();
Ext.Element.addMethods({
	setBox : function(e, g, b) {
		var d = this, a = e.width, c = e.height;
		if ((g && !d.autoBoxAdjust) && !d.isBorderBox()) {
			a -= (d.getBorderWidth("lr") + d.getPadding("lr"));
			c -= (d.getBorderWidth("tb") + d.getPadding("tb"))
		}
		d.setBounds(e.x, e.y, a, c, d.animTest.call(d, arguments, b, 2));
		return d
	},
	getBox : function(j, p) {
		var m = this, v, e, o, d = m.getBorderWidth, q = m.getPadding, g, a, u, n;
		if (!p) {
			v = m.getXY()
		} else {
			e = parseInt(m.getStyle("left"), 10) || 0;
			o = parseInt(m.getStyle("top"), 10) || 0;
			v = [ e, o ]
		}
		var c = m.dom, s = c.offsetWidth, i = c.offsetHeight, k;
		if (!j) {
			k = {
				x : v[0],
				y : v[1],
				0 : v[0],
				1 : v[1],
				width : s,
				height : i
			}
		} else {
			g = d.call(m, "l") + q.call(m, "l");
			a = d.call(m, "r") + q.call(m, "r");
			u = d.call(m, "t") + q.call(m, "t");
			n = d.call(m, "b") + q.call(m, "b");
			k = {
				x : v[0] + g,
				y : v[1] + u,
				0 : v[0] + g,
				1 : v[1] + u,
				width : s - (g + a),
				height : i - (u + n)
			}
		}
		k.right = k.x + k.width;
		k.bottom = k.y + k.height;
		return k
	},
	move : function(j, b, c) {
		var g = this, m = g.getXY(), k = m[0], i = m[1], d = [ k - b, i ], l = [ k + b, i ], h = [ k, i - b ], a = [ k,
				i + b ], e = {
			l : d,
			left : d,
			r : l,
			right : l,
			t : h,
			top : h,
			up : h,
			b : a,
			bottom : a,
			down : a
		};
		j = j.toLowerCase();
		g.moveTo(e[j][0], e[j][1], g.animTest.call(g, arguments, c, 2))
	},
	setLeftTop : function(d, c) {
		var b = this, a = b.dom.style;
		a.left = b.addUnits(d);
		a.top = b.addUnits(c);
		return b
	},
	getRegion : function() {
		return Ext.lib.Dom.getRegion(this.dom)
	},
	setBounds : function(b, g, d, a, c) {
		var e = this;
		if (!c || !e.anim) {
			e.setSize(d, a);
			e.setLocation(b, g)
		} else {
			e.anim({
				points : {
					to : [ b, g ]
				},
				width : {
					to : e.adjustWidth(d)
				},
				height : {
					to : e.adjustHeight(a)
				}
			}, e.preanim(arguments, 4), "motion")
		}
		return e
	},
	setRegion : function(b, a) {
		return this.setBounds(b.left, b.top, b.right - b.left, b.bottom - b.top, this.animTest.call(this, arguments, a,
				1))
	}
});
Ext.Element.addMethods({
	isScrollable : function() {
		var a = this.dom;
		return a.scrollHeight > a.clientHeight || a.scrollWidth > a.clientWidth
	},
	scrollTo : function(a, b) {
		this.dom["scroll" + (/top/i.test(a) ? "Top" : "Left")] = b;
		return this
	},
	getScroll : function() {
		var i = this.dom, h = document, a = h.body, c = h.documentElement, b, g, e;
		if (i == h || i == a) {
			if (Ext.isIE && Ext.isStrict) {
				b = c.scrollLeft;
				g = c.scrollTop
			} else {
				b = window.pageXOffset;
				g = window.pageYOffset
			}
			e = {
				left : b || (a ? a.scrollLeft : 0),
				top : g || (a ? a.scrollTop : 0)
			}
		} else {
			e = {
				left : i.scrollLeft,
				top : i.scrollTop
			}
		}
		return e
	}
});
Ext.Element
		.addMethods({
			scrollTo : function(c, e, b) {
				var a = /top/i, h = "scroll" + (a.test(c) ? "Top" : "Left"), d = this, g = d.dom;
				if (!b || !d.anim) {
					g[h] = e
				} else {
					d.anim({
						scroll : {
							to : a.test(h) ? [ g[h], e ] : [ e, g[h] ]
						}
					}, d.preanim(arguments, 2), "scroll")
				}
				return d
			},
			scrollIntoView : function(e, i) {
				var p = Ext.getDom(e) || Ext.getBody().dom, h = this.dom, g = this.getOffsetsTo(p), k = g[0]
						+ p.scrollLeft, u = g[1] + p.scrollTop, q = u + h.offsetHeight, d = k + h.offsetWidth, a = p.clientHeight, m = parseInt(
						p.scrollTop, 10), s = parseInt(p.scrollLeft, 10), j = m + a, n = s + p.clientWidth;
				if (h.offsetHeight > a || u < m) {
					p.scrollTop = u
				} else {
					if (q > j) {
						p.scrollTop = q - a
					}
				}
				p.scrollTop = p.scrollTop;
				if (i !== false) {
					if (h.offsetWidth > p.clientWidth || k < s) {
						p.scrollLeft = k
					} else {
						if (d > n) {
							p.scrollLeft = d - p.clientWidth
						}
					}
					p.scrollLeft = p.scrollLeft
				}
				return this
			},
			scrollChildIntoView : function(b, a) {
				Ext.fly(b, "_scrollChildIntoView").scrollIntoView(this, a)
			},
			scroll : function(m, b, d) {
				if (!this.isScrollable()) {
					return
				}
				var e = this.dom, g = e.scrollLeft, p = e.scrollTop, n = e.scrollWidth, k = e.scrollHeight, i = e.clientWidth, a = e.clientHeight, c = false, o, j = {
					l : Math.min(g + b, n - i),
					r : o = Math.max(g - b, 0),
					t : Math.max(p - b, 0),
					b : Math.min(p + b, k - a)
				};
				j.d = j.b;
				j.u = j.t;
				m = m.substr(0, 1);
				if ((o = j[m]) > -1) {
					c = true;
					this.scrollTo(m == "l" || m == "r" ? "left" : "top", o, this.preanim(arguments, 2))
				}
				return c
			}
		});
Ext.Element.VISIBILITY = 1;
Ext.Element.DISPLAY = 2;
Ext.Element
		.addMethods(function() {
			var h = "visibility", d = "display", b = "hidden", j = "none", a = "originalDisplay", c = "visibilityMode", e = Ext.Element.DISPLAY, g = Ext.Element.data, i = function(
					m) {
				var l = g(m, a);
				if (l === undefined) {
					g(m, a, l = "")
				}
				return l
			}, k = function(n) {
				var l = g(n, c);
				if (l === undefined) {
					g(n, c, l = 1)
				}
				return l
			};
			return {
				originalDisplay : "",
				visibilityMode : 1,
				setVisibilityMode : function(l) {
					g(this.dom, c, l);
					return this
				},
				animate : function(m, o, n, p, l) {
					this.anim(m, {
						duration : o,
						callback : n,
						easing : p
					}, l);
					return this
				},
				anim : function(o, p, m, r, n, l) {
					m = m || "run";
					p = p || {};
					var q = this, s = Ext.lib.Anim[m](q.dom, o, (p.duration || r) || 0.35,
							(p.easing || n) || "easeOut", function() {
								if (l) {
									l.call(q)
								}
								if (p.callback) {
									p.callback.call(p.scope || q, q, p)
								}
							}, q);
					p.anim = s;
					return s
				},
				preanim : function(l, m) {
					return !l[m] ? false : (Ext.isObject(l[m]) ? l[m] : {
						duration : l[m + 1],
						callback : l[m + 2],
						easing : l[m + 3]
					})
				},
				isVisible : function() {
					return !this.isStyle(h, b) && !this.isStyle(d, j)
				},
				setVisible : function(p, m) {
					var n = this, o = n.dom, l = k(this.dom) == e;
					if (!m || !n.anim) {
						if (l) {
							n.setDisplayed(p)
						} else {
							n.fixDisplay();
							o.style.visibility = p ? "visible" : b
						}
					} else {
						if (p) {
							n.setOpacity(0.01);
							n.setVisible(true)
						}
						n.anim({
							opacity : {
								to : (p ? 1 : 0)
							}
						}, n.preanim(arguments, 1), null, 0.35, "easeIn", function() {
							if (!p) {
								o.style[l ? d : h] = (l) ? j : b;
								Ext.fly(o).setOpacity(1)
							}
						})
					}
					return n
				},
				toggle : function(l) {
					var m = this;
					m.setVisible(!m.isVisible(), m.preanim(arguments, 0));
					return m
				},
				setDisplayed : function(l) {
					if (typeof l == "boolean") {
						l = l ? i(this.dom) : j
					}
					this.setStyle(d, l);
					return this
				},
				fixDisplay : function() {
					var l = this;
					if (l.isStyle(d, j)) {
						l.setStyle(h, b);
						l.setStyle(d, i(this.dom));
						if (l.isStyle(d, j)) {
							l.setStyle(d, "block")
						}
					}
				},
				hide : function(l) {
					this.setVisible(false, this.preanim(arguments, 0));
					return this
				},
				show : function(l) {
					this.setVisible(true, this.preanim(arguments, 0));
					return this
				}
			}
		}());
Ext.Element
		.addMethods(function() {
			var d = "visibility", b = "display", a = "hidden", h = "none", c = "x-masked", g = "x-masked-relative", e = Ext.Element.data;
			return {
				isVisible : function(i) {
					var j = !this.isStyle(d, a) && !this.isStyle(b, h), k = this.dom.parentNode;
					if (i !== true || !j) {
						return j
					}
					while (k && !/body/i.test(k.tagName)) {
						if (!Ext.fly(k, "_isVisible").isVisible()) {
							return false
						}
						k = k.parentNode
					}
					return true
				},
				isDisplayed : function() {
					return !this.isStyle(b, h)
				},
				enableDisplayMode : function(i) {
					this.setVisibilityMode(Ext.Element.DISPLAY);
					if (!Ext.isEmpty(i)) {
						e(this.dom, "originalDisplay", i)
					}
					return this
				},
				mask : function(j, n) {
					var p = this, l = p.dom, o = Ext.DomHelper, m = "ext-el-mask-msg", i, q;
					if (p.getStyle("position") == "static") {
						p.addClass(g)
					}
					if ((i = e(l, "maskMsg"))) {
						i.remove()
					}
					if ((i = e(l, "mask"))) {
						i.remove()
					}
					q = o.append(l, {
						cls : "ext-el-mask"
					}, true);
					e(l, "mask", q);
					p.addClass(c);
					q.setDisplayed(true);
					if (typeof j == "string") {
						var k = o.append(l, {
							cls : m,
							cn : {
								tag : "div"
							}
						}, true);
						e(l, "maskMsg", k);
						k.dom.className = n ? m + " " + n : m;
						k.dom.firstChild.innerHTML = j;
						k.setDisplayed(true);
						k.center(p)
					}
					if (Ext.isIE && !(Ext.isIE7 && Ext.isStrict) && p.getStyle("height") == "auto") {
						q.setSize(undefined, p.getHeight())
					}
					return q
				},
				unmask : function() {
					var k = this, l = k.dom, i = e(l, "mask"), j = e(l, "maskMsg");
					if (i) {
						if (j) {
							j.remove();
							e(l, "maskMsg", undefined)
						}
						i.remove();
						e(l, "mask", undefined)
					}
					k.removeClass([ c, g ])
				},
				isMasked : function() {
					var i = e(this.dom, "mask");
					return i && i.isVisible()
				},
				createShim : function() {
					var i = document.createElement("iframe"), j;
					i.frameBorder = "0";
					i.className = "ext-shim";
					if (Ext.isIE && Ext.isSecure) {
						i.src = Ext.SSL_SECURE_URL
					}
					j = Ext.get(this.dom.parentNode.insertBefore(i, this.dom));
					j.autoBoxAdjust = false;
					return j
				}
			}
		}());
Ext.Element.addMethods({
	addKeyListener : function(b, d, c) {
		var a;
		if (!Ext.isObject(b) || Ext.isArray(b)) {
			a = {
				key : b,
				fn : d,
				scope : c
			}
		} else {
			a = {
				key : b.key,
				shift : b.shift,
				ctrl : b.ctrl,
				alt : b.alt,
				fn : d,
				scope : c
			}
		}
		return new Ext.KeyMap(this, a)
	},
	addKeyMap : function(a) {
		return new Ext.KeyMap(this, a)
	}
});
(function() {
	var y = null, A = undefined, k = true, t = false, j = "setX", h = "setY", a = "setXY", n = "left", l = "bottom", s = "top", m = "right", q = "height", g = "width", i = "points", w = "hidden", z = "absolute", u = "visible", e = "motion", o = "position", r = "easeOut", d = new Ext.Element.Flyweight(), v = {}, x = function(
			B) {
		return B || {}
	}, p = function(B) {
		d.dom = B;
		d.id = Ext.id(B);
		return d
	}, c = function(B) {
		if (!v[B]) {
			v[B] = []
		}
		return v[B]
	}, b = function(C, B) {
		v[C] = B
	};
	Ext.enableFx = k;
	Ext.Fx = {
		switchStatements : function(C, D, B) {
			return D.apply(this, B[C])
		},
		slideIn : function(H, E) {
			E = x(E);
			var J = this, G = J.dom, M = G.style, O, B, L, D, C, M, I, N, K, F;
			H = H || "t";
			J.queueFx(E, function() {
				O = p(G).getXY();
				p(G).fixDisplay();
				B = p(G).getFxRestore();
				L = {
					x : O[0],
					y : O[1],
					0 : O[0],
					1 : O[1],
					width : G.offsetWidth,
					height : G.offsetHeight
				};
				L.right = L.x + L.width;
				L.bottom = L.y + L.height;
				p(G).setWidth(L.width).setHeight(L.height);
				D = p(G).fxWrap(B.pos, E, w);
				M.visibility = u;
				M.position = z;
				function P() {
					p(G).fxUnwrap(D, B.pos, E);
					M.width = B.width;
					M.height = B.height;
					p(G).afterFx(E)
				}
				N = {
					to : [ L.x, L.y ]
				};
				K = {
					to : L.width
				};
				F = {
					to : L.height
				};
				function Q(U, R, V, S, X, Z, ac, ab, aa, W, T) {
					var Y = {};
					p(U).setWidth(V).setHeight(S);
					if (p(U)[X]) {
						p(U)[X](Z)
					}
					R[ac] = R[ab] = "0";
					if (aa) {
						Y.width = aa
					}
					if (W) {
						Y.height = W
					}
					if (T) {
						Y.points = T
					}
					return Y
				}
				I = p(G).switchStatements(H.toLowerCase(), Q, {
					t : [ D, M, L.width, 0, y, y, n, l, y, F, y ],
					l : [ D, M, 0, L.height, y, y, m, s, K, y, y ],
					r : [ D, M, L.width, L.height, j, L.right, n, s, y, y, N ],
					b : [ D, M, L.width, L.height, h, L.bottom, n, s, y, F, N ],
					tl : [ D, M, 0, 0, y, y, m, l, K, F, N ],
					bl : [ D, M, 0, 0, h, L.y + L.height, m, s, K, F, N ],
					br : [ D, M, 0, 0, a, [ L.right, L.bottom ], n, s, K, F, N ],
					tr : [ D, M, 0, 0, j, L.x + L.width, n, l, K, F, N ]
				});
				M.visibility = u;
				p(D).show();
				arguments.callee.anim = p(D).fxanim(I, E, e, 0.5, r, P)
			});
			return J
		},
		slideOut : function(F, D) {
			D = x(D);
			var H = this, E = H.dom, K = E.style, L = H.getXY(), C, B, I, J, G = {
				to : 0
			};
			F = F || "t";
			H.queueFx(D, function() {
				B = p(E).getFxRestore();
				I = {
					x : L[0],
					y : L[1],
					0 : L[0],
					1 : L[1],
					width : E.offsetWidth,
					height : E.offsetHeight
				};
				I.right = I.x + I.width;
				I.bottom = I.y + I.height;
				p(E).setWidth(I.width).setHeight(I.height);
				C = p(E).fxWrap(B.pos, D, u);
				K.visibility = u;
				K.position = z;
				p(C).setWidth(I.width).setHeight(I.height);
				function M() {
					D.useDisplay ? p(E).setDisplayed(t) : p(E).hide();
					p(E).fxUnwrap(C, B.pos, D);
					K.width = B.width;
					K.height = B.height;
					p(E).afterFx(D)
				}
				function N(O, W, U, X, S, V, R, T, Q) {
					var P = {};
					O[W] = O[U] = "0";
					P[X] = S;
					if (V) {
						P[V] = R
					}
					if (T) {
						P[T] = Q
					}
					return P
				}
				J = p(E).switchStatements(F.toLowerCase(), N, {
					t : [ K, n, l, q, G ],
					l : [ K, m, s, g, G ],
					r : [ K, n, s, g, G, i, {
						to : [ I.right, I.y ]
					} ],
					b : [ K, n, s, q, G, i, {
						to : [ I.x, I.bottom ]
					} ],
					tl : [ K, m, l, g, G, q, G ],
					bl : [ K, m, s, g, G, q, G, i, {
						to : [ I.x, I.bottom ]
					} ],
					br : [ K, n, s, g, G, q, G, i, {
						to : [ I.x + I.width, I.bottom ]
					} ],
					tr : [ K, n, l, g, G, q, G, i, {
						to : [ I.right, I.y ]
					} ]
				});
				arguments.callee.anim = p(C).fxanim(J, D, e, 0.5, r, M)
			});
			return H
		},
		puff : function(H) {
			H = x(H);
			var F = this, G = F.dom, C = G.style, D, B, E;
			F.queueFx(H, function() {
				D = p(G).getWidth();
				B = p(G).getHeight();
				p(G).clearOpacity();
				p(G).show();
				E = p(G).getFxRestore();
				function I() {
					H.useDisplay ? p(G).setDisplayed(t) : p(G).hide();
					p(G).clearOpacity();
					p(G).setPositioning(E.pos);
					C.width = E.width;
					C.height = E.height;
					C.fontSize = "";
					p(G).afterFx(H)
				}
				arguments.callee.anim = p(G).fxanim({
					width : {
						to : p(G).adjustWidth(D * 2)
					},
					height : {
						to : p(G).adjustHeight(B * 2)
					},
					points : {
						by : [ -D * 0.5, -B * 0.5 ]
					},
					opacity : {
						to : 0
					},
					fontSize : {
						to : 200,
						unit : "%"
					}
				}, H, e, 0.5, r, I)
			});
			return F
		},
		switchOff : function(F) {
			F = x(F);
			var D = this, E = D.dom, B = E.style, C;
			D.queueFx(F, function() {
				p(E).clearOpacity();
				p(E).clip();
				C = p(E).getFxRestore();
				function G() {
					F.useDisplay ? p(E).setDisplayed(t) : p(E).hide();
					p(E).clearOpacity();
					p(E).setPositioning(C.pos);
					B.width = C.width;
					B.height = C.height;
					p(E).afterFx(F)
				}
				p(E).fxanim({
					opacity : {
						to : 0.3
					}
				}, y, y, 0.1, y, function() {
					p(E).clearOpacity();
					(function() {
						p(E).fxanim({
							height : {
								to : 1
							},
							points : {
								by : [ 0, p(E).getHeight() * 0.5 ]
							}
						}, F, e, 0.3, "easeIn", G)
					}).defer(100)
				})
			});
			return D
		},
		highlight : function(D, H) {
			H = x(H);
			var F = this, G = F.dom, B = H.attr || "backgroundColor", C = {}, E;
			F.queueFx(H, function() {
				p(G).clearOpacity();
				p(G).show();
				function I() {
					G.style[B] = E;
					p(G).afterFx(H)
				}
				E = G.style[B];
				C[B] = {
					from : D || "ffff9c",
					to : H.endColor || p(G).getColor(B) || "ffffff"
				};
				arguments.callee.anim = p(G).fxanim(C, H, "color", 1, "easeIn", I)
			});
			return F
		},
		frame : function(B, E, H) {
			H = x(H);
			var D = this, G = D.dom, C, F;
			D.queueFx(H, function() {
				B = B || "#C3DAF9";
				if (B.length == 6) {
					B = "#" + B
				}
				E = E || 1;
				p(G).show();
				var L = p(G).getXY(), J = {
					x : L[0],
					y : L[1],
					0 : L[0],
					1 : L[1],
					width : G.offsetWidth,
					height : G.offsetHeight
				}, I = function() {
					C = p(document.body || document.documentElement).createChild({
						style : {
							visbility : w,
							position : z,
							"z-index" : 35000,
							border : "0px solid " + B
						}
					});
					return C.queueFx({}, K)
				};
				arguments.callee.anim = {
					isAnimated : true,
					stop : function() {
						E = 0;
						C.stopFx()
					}
				};
				function K() {
					var M = Ext.isBorderBox ? 2 : 1;
					F = C.anim({
						top : {
							from : J.y,
							to : J.y - 20
						},
						left : {
							from : J.x,
							to : J.x - 20
						},
						borderWidth : {
							from : 0,
							to : 10
						},
						opacity : {
							from : 1,
							to : 0
						},
						height : {
							from : J.height,
							to : J.height + 20 * M
						},
						width : {
							from : J.width,
							to : J.width + 20 * M
						}
					}, {
						duration : H.duration || 1,
						callback : function() {
							C.remove();
							--E > 0 ? I() : p(G).afterFx(H)
						}
					});
					arguments.callee.anim = {
						isAnimated : true,
						stop : function() {
							F.stop()
						}
					}
				}
				I()
			});
			return D
		},
		pause : function(D) {
			var C = this.dom, B;
			this.queueFx({}, function() {
				B = setTimeout(function() {
					p(C).afterFx({})
				}, D * 1000);
				arguments.callee.anim = {
					isAnimated : true,
					stop : function() {
						clearTimeout(B);
						p(C).afterFx({})
					}
				}
			});
			return this
		},
		fadeIn : function(D) {
			D = x(D);
			var B = this, C = B.dom, E = D.endOpacity || 1;
			B.queueFx(D, function() {
				p(C).setOpacity(0);
				p(C).fixDisplay();
				C.style.visibility = u;
				arguments.callee.anim = p(C).fxanim({
					opacity : {
						to : E
					}
				}, D, y, 0.5, r, function() {
					if (E == 1) {
						p(C).clearOpacity()
					}
					p(C).afterFx(D)
				})
			});
			return B
		},
		fadeOut : function(E) {
			E = x(E);
			var C = this, D = C.dom, B = D.style, F = E.endOpacity || 0;
			C.queueFx(E,
					function() {
						arguments.callee.anim = p(D)
								.fxanim(
										{
											opacity : {
												to : F
											}
										},
										E,
										y,
										0.5,
										r,
										function() {
											if (F == 0) {
												Ext.Element.data(D, "visibilityMode") == Ext.Element.DISPLAY
														|| E.useDisplay ? B.display = "none" : B.visibility = w;
												p(D).clearOpacity()
											}
											p(D).afterFx(E)
										})
					});
			return C
		},
		scale : function(B, C, D) {
			this.shift(Ext.apply({}, D, {
				width : B,
				height : C
			}));
			return this
		},
		shift : function(D) {
			D = x(D);
			var C = this.dom, B = {};
			this.queueFx(D, function() {
				for ( var E in D) {
					if (D[E] != A) {
						B[E] = {
							to : D[E]
						}
					}
				}
				B.width ? B.width.to = p(C).adjustWidth(D.width) : B;
				B.height ? B.height.to = p(C).adjustWidth(D.height) : B;
				if (B.x || B.y || B.xy) {
					B.points = B.xy || {
						to : [ B.x ? B.x.to : p(C).getX(), B.y ? B.y.to : p(C).getY() ]
					}
				}
				arguments.callee.anim = p(C).fxanim(B, D, e, 0.35, r, function() {
					p(C).afterFx(D)
				})
			});
			return this
		},
		ghost : function(E, C) {
			C = x(C);
			var G = this, D = G.dom, J = D.style, H = {
				opacity : {
					to : 0
				},
				points : {}
			}, K = H.points, B, I, F;
			E = E || "b";
			G.queueFx(C, function() {
				B = p(D).getFxRestore();
				I = p(D).getWidth();
				F = p(D).getHeight();
				function L() {
					C.useDisplay ? p(D).setDisplayed(t) : p(D).hide();
					p(D).clearOpacity();
					p(D).setPositioning(B.pos);
					J.width = B.width;
					J.height = B.height;
					p(D).afterFx(C)
				}
				K.by = p(D).switchStatements(E.toLowerCase(), function(N, M) {
					return [ N, M ]
				}, {
					t : [ 0, -F ],
					l : [ -I, 0 ],
					r : [ I, 0 ],
					b : [ 0, F ],
					tl : [ -I, -F ],
					bl : [ -I, F ],
					br : [ I, F ],
					tr : [ I, -F ]
				});
				arguments.callee.anim = p(D).fxanim(H, C, e, 0.5, r, L)
			});
			return G
		},
		syncFx : function() {
			var B = this;
			B.fxDefaults = Ext.apply(B.fxDefaults || {}, {
				block : t,
				concurrent : k,
				stopFx : t
			});
			return B
		},
		sequenceFx : function() {
			var B = this;
			B.fxDefaults = Ext.apply(B.fxDefaults || {}, {
				block : t,
				concurrent : t,
				stopFx : t
			});
			return B
		},
		nextFx : function() {
			var B = c(this.dom.id)[0];
			if (B) {
				B.call(this)
			}
		},
		hasActiveFx : function() {
			return c(this.dom.id)[0]
		},
		stopFx : function(B) {
			var C = this, E = C.dom.id;
			if (C.hasActiveFx()) {
				var D = c(E)[0];
				if (D && D.anim) {
					if (D.anim.isAnimated) {
						b(E, [ D ]);
						D.anim.stop(B !== undefined ? B : k)
					} else {
						b(E, [])
					}
				}
			}
			return C
		},
		beforeFx : function(B) {
			if (this.hasActiveFx() && !B.concurrent) {
				if (B.stopFx) {
					this.stopFx();
					return k
				}
				return t
			}
			return k
		},
		hasFxBlock : function() {
			var B = c(this.dom.id);
			return B && B[0] && B[0].block
		},
		queueFx : function(E, B) {
			var C = this;
			if (!C.hasFxBlock()) {
				Ext.applyIf(E, C.fxDefaults);
				if (!E.concurrent) {
					var D = C.beforeFx(E);
					B.block = E.block;
					c(C.dom.id).push(B);
					if (D) {
						C.nextFx()
					}
				} else {
					B.call(C)
				}
			}
			return C
		},
		fxWrap : function(H, F, D) {
			var E = this.dom, C, B;
			if (!F.wrap || !(C = Ext.getDom(F.wrap))) {
				if (F.fixPosition) {
					B = p(E).getXY()
				}
				var G = document.createElement("div");
				G.style.visibility = D;
				C = E.parentNode.insertBefore(G, E);
				p(C).setPositioning(H);
				if (p(C).isStyle(o, "static")) {
					p(C).position("relative")
				}
				p(E).clearPositioning("auto");
				p(C).clip();
				C.appendChild(E);
				if (B) {
					p(C).setXY(B)
				}
			}
			return C
		},
		fxUnwrap : function(B, E, D) {
			var C = this.dom;
			p(C).clearPositioning();
			p(C).setPositioning(E);
			if (!D.wrap) {
				B.parentNode.insertBefore(C, B);
				p(B).remove()
			}
		},
		getFxRestore : function() {
			var B = this.dom.style;
			return {
				pos : this.getPositioning(),
				width : B.width,
				height : B.height
			}
		},
		afterFx : function(C) {
			var B = this.dom, E = B.id, D = !C.concurrent;
			if (C.afterStyle) {
				p(B).setStyle(C.afterStyle)
			}
			if (C.afterCls) {
				p(B).addClass(C.afterCls)
			}
			if (C.remove == k) {
				p(B).remove()
			}
			if (D) {
				c(E).shift()
			}
			if (C.callback) {
				C.callback.call(C.scope, p(B))
			}
			if (D) {
				p(B).nextFx()
			}
		},
		fxanim : function(E, F, C, G, D, B) {
			C = C || "run";
			F = F || {};
			var H = Ext.lib.Anim[C](this.dom, E, (F.duration || G) || 0.35, (F.easing || D) || r, B, this);
			F.anim = H;
			return H
		}
	};
	Ext.Fx.resize = Ext.Fx.scale;
	Ext.Element.addMethods(Ext.Fx)
})();
Ext.CompositeElementLite = function(b, a) {
	this.elements = [];
	this.add(b, a);
	this.el = new Ext.Element.Flyweight()
};
Ext.CompositeElementLite.prototype = {
	isComposite : true,
	getCount : function() {
		return this.elements.length
	},
	add : function(b) {
		if (b) {
			if (Ext.isArray(b)) {
				this.elements = this.elements.concat(b)
			} else {
				var a = this.elements;
				Ext.each(b, function(c) {
					a.push(c)
				})
			}
		}
		return this
	},
	invoke : function(d, a) {
		var b = this.elements, c = this.el;
		Ext.each(b, function(g) {
			c.dom = g;
			Ext.Element.prototype[d].apply(c, a)
		});
		return this
	},
	item : function(a) {
		var b = this;
		if (!b.elements[a]) {
			return null
		}
		b.el.dom = b.elements[a];
		return b.el
	},
	addListener : function(a, d, c, b) {
		Ext.each(this.elements, function(g) {
			Ext.EventManager.on(g, a, d, c || g, b)
		});
		return this
	},
	each : function(c, b) {
		var d = this, a = d.el;
		Ext.each(d.elements, function(h, g) {
			a.dom = h;
			return c.call(b || a, a, d, g)
		});
		return d
	},
	indexOf : function(a) {
		return this.elements.indexOf(Ext.getDom(a))
	},
	replaceElement : function(e, c, a) {
		var b = !isNaN(e) ? e : this.indexOf(e), g;
		if (b > -1) {
			c = Ext.getDom(c);
			if (a) {
				g = this.elements[b];
				g.parentNode.insertBefore(c, g);
				Ext.removeNode(g)
			}
			this.elements.splice(b, 1, c)
		}
		return this
	},
	clear : function() {
		this.elements = []
	}
};
Ext.CompositeElementLite.prototype.on = Ext.CompositeElementLite.prototype.addListener;
(function() {
	var c, b = Ext.Element.prototype, a = Ext.CompositeElementLite.prototype;
	for (c in b) {
		if (Ext.isFunction(b[c])) {
			(function(d) {
				a[d] = a[d] || function() {
					return this.invoke(d, arguments)
				}
			}).call(a, c)
		}
	}
})();
if (Ext.DomQuery) {
	Ext.Element.selectorFunction = Ext.DomQuery.select
}
Ext.Element.select = function(a, d, b) {
	var c;
	if (typeof a == "string") {
		c = Ext.Element.selectorFunction(a, b)
	} else {
		if (a.length !== undefined) {
			c = a
		} else {
			throw "Invalid selector"
		}
	}
	return new Ext.CompositeElementLite(c)
};
Ext.select = Ext.Element.select;
Ext.apply(Ext.CompositeElementLite.prototype, {
	addElements : function(c, a) {
		if (!c) {
			return this
		}
		if (typeof c == "string") {
			c = Ext.Element.selectorFunction(c, a)
		}
		var b = this.elements;
		Ext.each(c, function(d) {
			b.push(Ext.get(d))
		});
		return this
	},
	fill : function(a) {
		this.elements = [];
		this.add(a);
		return this
	},
	first : function() {
		return this.item(0)
	},
	last : function() {
		return this.item(this.getCount() - 1)
	},
	contains : function(a) {
		return this.indexOf(a) != -1
	},
	filter : function(a) {
		var b = [];
		this.each(function(c) {
			if (c.is(a)) {
				b[b.length] = c.dom
			}
		});
		this.fill(b);
		return this
	},
	removeElement : function(d, e) {
		var c = this, a = this.elements, b;
		Ext.each(d, function(g) {
			if ((b = (a[g] || a[g = c.indexOf(g)]))) {
				if (e) {
					if (b.dom) {
						b.remove()
					} else {
						Ext.removeNode(b)
					}
				}
				a.splice(g, 1)
			}
		});
		return this
	}
});
Ext.CompositeElement = function(b, a) {
	this.elements = [];
	this.add(b, a)
};
Ext.extend(Ext.CompositeElement, Ext.CompositeElementLite, {
	invoke : function(b, a) {
		Ext.each(this.elements, function(c) {
			Ext.Element.prototype[b].apply(c, a)
		});
		return this
	},
	add : function(c, a) {
		if (!c) {
			return this
		}
		if (typeof c == "string") {
			c = Ext.Element.selectorFunction(c, a)
		}
		var b = this.elements;
		Ext.each(c, function(d) {
			b.push(Ext.get(d))
		});
		return this
	},
	item : function(a) {
		return this.elements[a] || null
	},
	indexOf : function(a) {
		return this.elements.indexOf(Ext.get(a))
	},
	filter : function(a) {
		var c = this, b = [];
		Ext.each(c.elements, function(d) {
			if (d.is(a)) {
				b.push(Ext.get(d))
			}
		});
		c.elements = b;
		return c
	},
	each : function(b, a) {
		Ext.each(this.elements, function(d, c) {
			return b.call(a || d, d, this, c)
		}, this);
		return this
	}
});
Ext.Element.select = function(a, d, b) {
	var c;
	if (typeof a == "string") {
		c = Ext.Element.selectorFunction(a, b)
	} else {
		if (a.length !== undefined) {
			c = a
		} else {
			throw "Invalid selector"
		}
	}
	return (d === true) ? new Ext.CompositeElement(c) : new Ext.CompositeElementLite(c)
};
Ext.select = Ext.Element.select;
(function() {
	var c = "beforerequest", l = "requestcomplete", k = "requestexception", e = undefined, i = "load", h = "POST", j = "GET", g = window;
	Ext.data.Connection = function(m) {
		Ext.apply(this, m);
		this.addEvents(c, l, k);
		Ext.data.Connection.superclass.constructor.call(this)
	};
	function b(m) {
		this.transId = false;
		var n = m.argument.options;
		m.argument = n ? n.argument : null;
		this.fireEvent(l, this, m, n);
		if (n.success) {
			n.success.call(n.scope, m, n)
		}
		if (n.callback) {
			n.callback.call(n.scope, n, true, m)
		}
	}
	function d(m, o) {
		this.transId = false;
		var n = m.argument.options;
		m.argument = n ? n.argument : null;
		this.fireEvent(k, this, m, n, o);
		if (n.failure) {
			n.failure.call(n.scope, m, n)
		}
		if (n.callback) {
			n.callback.call(n.scope, n, false, m)
		}
	}
	function a(t, m, n) {
		var p = Ext.id(), z = document, u = z.createElement("iframe"), q = Ext.getDom(t.form), y = [], x, s = "multipart/form-data", r = {
			target : q.target,
			method : q.method,
			encoding : q.encoding,
			enctype : q.enctype,
			action : q.action
		};
		Ext.apply(u, {
			id : p,
			name : p,
			className : "x-hidden",
			src : Ext.SSL_SECURE_URL
		});
		z.body.appendChild(u);
		if (Ext.isIE) {
			document.frames[p].name = p
		}
		Ext.apply(q, {
			target : p,
			method : h,
			enctype : s,
			encoding : s,
			action : n || r.action
		});
		m = Ext.urlDecode(m, false);
		for ( var w in m) {
			if (m.hasOwnProperty(w)) {
				x = z.createElement("input");
				x.type = "hidden";
				x.value = m[x.name = w];
				q.appendChild(x);
				y.push(x)
			}
		}
		function v() {
			var B = this, A = {
				responseText : "",
				responseXML : null,
				argument : t.argument
			}, E, D;
			try {
				E = u.contentWindow.document || u.contentDocument || g.frames[p].document;
				if (E) {
					if (E.body) {
						if (/textarea/i.test((D = E.body.firstChild || {}).tagName)) {
							A.responseText = D.value
						} else {
							A.responseText = E.body.innerHTML
						}
					}
					A.responseXML = E.XMLDocument || E
				}
			} catch (C) {
			}
			Ext.EventManager.removeListener(u, i, v, B);
			B.fireEvent(l, B, A, t);
			function o(H, G, F) {
				if (Ext.isFunction(H)) {
					H.apply(G, F)
				}
			}
			o(t.success, t.scope, [ A, t ]);
			o(t.callback, t.scope, [ t, true, A ]);
			if (!B.debugUploads) {
				setTimeout(function() {
					Ext.removeNode(u)
				}, 100)
			}
		}
		Ext.EventManager.on(u, i, v, this);
		q.submit();
		Ext.apply(q, r);
		Ext.each(y, function(o) {
			Ext.removeNode(o)
		})
	}
	Ext.extend(Ext.data.Connection, Ext.util.Observable, {
		timeout : 30000,
		autoAbort : false,
		disableCaching : true,
		disableCachingParam : "_dc",
		request : function(s) {
			var v = this;
			if (v.fireEvent(c, v, s)) {
				if (s.el) {
					if (!Ext.isEmpty(s.indicatorText)) {
						v.indicatorText = '<div class="loading-indicator">' + s.indicatorText + "</div>"
					}
					if (v.indicatorText) {
						Ext.getDom(s.el).innerHTML = v.indicatorText
					}
					s.success = (Ext.isFunction(s.success) ? s.success : function() {
					}).createInterceptor(function(o) {
						Ext.getDom(s.el).innerHTML = o.responseText
					})
				}
				var q = s.params, n = s.url || v.url, m, t = {
					success : b,
					failure : d,
					scope : v,
					argument : {
						options : s
					},
					timeout : s.timeout || v.timeout
				}, r, w;
				if (Ext.isFunction(q)) {
					q = q.call(s.scope || g, s)
				}
				q = Ext.urlEncode(v.extraParams, Ext.isObject(q) ? Ext.urlEncode(q) : q);
				if (Ext.isFunction(n)) {
					n = n.call(s.scope || g, s)
				}
				if ((r = Ext.getDom(s.form))) {
					n = n || r.action;
					if (s.isUpload || /multipart\/form-data/i.test(r.getAttribute("enctype"))) {
						return a.call(v, s, q, n)
					}
					w = Ext.lib.Ajax.serializeForm(r);
					q = q ? (q + "&" + w) : w
				}
				m = s.method || v.method || ((q || s.xmlData || s.jsonData) ? h : j);
				if (m === j && (v.disableCaching && s.disableCaching !== false) || s.disableCaching === true) {
					var u = s.disableCachingParam || v.disableCachingParam;
					n = Ext.urlAppend(n, u + "=" + (new Date().getTime()))
				}
				s.headers = Ext.apply(s.headers || {}, v.defaultHeaders || {});
				if (s.autoAbort === true || v.autoAbort) {
					v.abort()
				}
				if ((m == j || s.xmlData || s.jsonData) && q) {
					n = Ext.urlAppend(n, q);
					q = ""
				}
				return (v.transId = Ext.lib.Ajax.request(m, n, t, q, s))
			} else {
				return s.callback ? s.callback.apply(s.scope, [ s, e, e ]) : null
			}
		},
		isLoading : function(m) {
			return m ? Ext.lib.Ajax.isCallInProgress(m) : !!this.transId
		},
		abort : function(m) {
			if (m || this.isLoading()) {
				Ext.lib.Ajax.abort(m || this.transId)
			}
		}
	})
})();
Ext.Ajax = new Ext.data.Connection({
	autoAbort : false,
	serializeForm : function(a) {
		return Ext.lib.Ajax.serializeForm(a)
	}
});
Ext.UpdateManager = Ext.Updater = Ext.extend(Ext.util.Observable, function() {
	var b = "beforeupdate", d = "update", c = "failure";
	function a(h) {
		var i = this;
		i.transaction = null;
		if (h.argument.form && h.argument.reset) {
			try {
				h.argument.form.reset()
			} catch (j) {
			}
		}
		if (i.loadScripts) {
			i.renderer.render(i.el, h, i, g.createDelegate(i, [ h ]))
		} else {
			i.renderer.render(i.el, h, i);
			g.call(i, h)
		}
	}
	function g(h, i, j) {
		this.fireEvent(i || d, this.el, h);
		if (Ext.isFunction(h.argument.callback)) {
			h.argument.callback.call(h.argument.scope, this.el, Ext.isEmpty(j) ? true : false, h, h.argument.options)
		}
	}
	function e(h) {
		g.call(this, h, c, !!(this.transaction = null))
	}
	return {
		constructor : function(i, h) {
			var j = this;
			i = Ext.get(i);
			if (!h && i.updateManager) {
				return i.updateManager
			}
			j.el = i;
			j.defaultUrl = null;
			j.addEvents(b, d, c);
			Ext.apply(j, Ext.Updater.defaults);
			j.transaction = null;
			j.refreshDelegate = j.refresh.createDelegate(j);
			j.updateDelegate = j.update.createDelegate(j);
			j.formUpdateDelegate = (j.formUpdate || function() {
			}).createDelegate(j);
			j.renderer = j.renderer || j.getDefaultRenderer();
			Ext.Updater.superclass.constructor.call(j)
		},
		setRenderer : function(h) {
			this.renderer = h
		},
		getRenderer : function() {
			return this.renderer
		},
		getDefaultRenderer : function() {
			return new Ext.Updater.BasicRenderer()
		},
		setDefaultUrl : function(h) {
			this.defaultUrl = h
		},
		getEl : function() {
			return this.el
		},
		update : function(i, n, p, l) {
			var k = this, h, j;
			if (k.fireEvent(b, k.el, i, n) !== false) {
				if (Ext.isObject(i)) {
					h = i;
					i = h.url;
					n = n || h.params;
					p = p || h.callback;
					l = l || h.discardUrl;
					j = h.scope;
					if (!Ext.isEmpty(h.nocache)) {
						k.disableCaching = h.nocache
					}
					if (!Ext.isEmpty(h.text)) {
						k.indicatorText = '<div class="loading-indicator">' + h.text + "</div>"
					}
					if (!Ext.isEmpty(h.scripts)) {
						k.loadScripts = h.scripts
					}
					if (!Ext.isEmpty(h.timeout)) {
						k.timeout = h.timeout
					}
				}
				k.showLoading();
				if (!l) {
					k.defaultUrl = i
				}
				if (Ext.isFunction(i)) {
					i = i.call(k)
				}
				var m = Ext.apply({}, {
					url : i,
					params : (Ext.isFunction(n) && j) ? n.createDelegate(j) : n,
					success : a,
					failure : e,
					scope : k,
					callback : undefined,
					timeout : (k.timeout * 1000),
					disableCaching : k.disableCaching,
					argument : {
						options : h,
						url : i,
						form : null,
						callback : p,
						scope : j || window,
						params : n
					}
				}, h);
				k.transaction = Ext.Ajax.request(m)
			}
		},
		formUpdate : function(k, h, j, l) {
			var i = this;
			if (i.fireEvent(b, i.el, k, h) !== false) {
				if (Ext.isFunction(h)) {
					h = h.call(i)
				}
				k = Ext.getDom(k);
				i.transaction = Ext.Ajax.request({
					form : k,
					url : h,
					success : a,
					failure : e,
					scope : i,
					timeout : (i.timeout * 1000),
					argument : {
						url : h,
						form : k,
						callback : l,
						reset : j
					}
				});
				i.showLoading.defer(1, i)
			}
		},
		startAutoRefresh : function(i, j, l, m, h) {
			var k = this;
			if (h) {
				k.update(j || k.defaultUrl, l, m, true)
			}
			if (k.autoRefreshProcId) {
				clearInterval(k.autoRefreshProcId)
			}
			k.autoRefreshProcId = setInterval(k.update.createDelegate(k, [ j || k.defaultUrl, l, m, true ]), i * 1000)
		},
		stopAutoRefresh : function() {
			if (this.autoRefreshProcId) {
				clearInterval(this.autoRefreshProcId);
				delete this.autoRefreshProcId
			}
		},
		isAutoRefreshing : function() {
			return !!this.autoRefreshProcId
		},
		showLoading : function() {
			if (this.showLoadIndicator) {
				this.el.dom.innerHTML = this.indicatorText
			}
		},
		abort : function() {
			if (this.transaction) {
				Ext.Ajax.abort(this.transaction)
			}
		},
		isUpdating : function() {
			return this.transaction ? Ext.Ajax.isLoading(this.transaction) : false
		},
		refresh : function(h) {
			if (this.defaultUrl) {
				this.update(this.defaultUrl, null, h, true)
			}
		}
	}
}());
Ext.Updater.defaults = {
	timeout : 30,
	disableCaching : false,
	showLoadIndicator : true,
	indicatorText : '<div class="loading-indicator">Loading...</div>',
	loadScripts : false,
	sslBlankUrl : (Ext.SSL_SECURE_URL || "javascript:false")
};
Ext.Updater.updateElement = function(d, c, e, b) {
	var a = Ext.get(d).getUpdater();
	Ext.apply(a, b);
	a.update(c, e, b ? b.callback : null)
};
Ext.Updater.BasicRenderer = function() {
};
Ext.Updater.BasicRenderer.prototype = {
	render : function(c, a, b, d) {
		c.update(a.responseText, b.loadScripts, d)
	}
};
(function() {
	Date.useStrict = false;
	function b(d) {
		var c = Array.prototype.slice.call(arguments, 1);
		return d.replace(/\{(\d+)\}/g, function(e, g) {
			return c[g]
		})
	}
	Date.formatCodeToRegex = function(d, c) {
		var e = Date.parseCodes[d];
		if (e) {
			e = typeof e == "function" ? e() : e;
			Date.parseCodes[d] = e
		}
		return e ? Ext.applyIf({
			c : e.c ? b(e.c, c || "{0}") : e.c
		}, e) : {
			g : 0,
			c : null,
			s : Ext.escapeRe(d)
		}
	};
	var a = Date.formatCodeToRegex;
	Ext
			.apply(
					Date,
					{
						parseFunctions : {
							"M$" : function(d, c) {
								var e = new RegExp("\\/Date\\(([-+])?(\\d+)(?:[+-]\\d{4})?\\)\\/");
								var g = (d || "").match(e);
								return g ? new Date(((g[1] || "") + g[2]) * 1) : null
							}
						},
						parseRegexes : [],
						formatFunctions : {
							"M$" : function() {
								return "\\/Date(" + this.getTime() + ")\\/"
							}
						},
						y2kYear : 50,
						MILLI : "ms",
						SECOND : "s",
						MINUTE : "mi",
						HOUR : "h",
						DAY : "d",
						MONTH : "mo",
						YEAR : "y",
						defaults : {},
						dayNames : [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" ],
						monthNames : [ "January", "February", "March", "April", "May", "June", "July", "August",
								"September", "October", "November", "December" ],
						monthNumbers : {
							Jan : 0,
							Feb : 1,
							Mar : 2,
							Apr : 3,
							May : 4,
							Jun : 5,
							Jul : 6,
							Aug : 7,
							Sep : 8,
							Oct : 9,
							Nov : 10,
							Dec : 11
						},
						getShortMonthName : function(c) {
							return Date.monthNames[c].substring(0, 3)
						},
						getShortDayName : function(c) {
							return Date.dayNames[c].substring(0, 3)
						},
						getMonthNumber : function(c) {
							return Date.monthNumbers[c.substring(0, 1).toUpperCase() + c.substring(1, 3).toLowerCase()]
						},
						formatCodes : {
							d : "String.leftPad(this.getDate(), 2, '0')",
							D : "Date.getShortDayName(this.getDay())",
							j : "this.getDate()",
							l : "Date.dayNames[this.getDay()]",
							N : "(this.getDay() ? this.getDay() : 7)",
							S : "this.getSuffix()",
							w : "this.getDay()",
							z : "this.getDayOfYear()",
							W : "String.leftPad(this.getWeekOfYear(), 2, '0')",
							F : "Date.monthNames[this.getMonth()]",
							m : "String.leftPad(this.getMonth() + 1, 2, '0')",
							M : "Date.getShortMonthName(this.getMonth())",
							n : "(this.getMonth() + 1)",
							t : "this.getDaysInMonth()",
							L : "(this.isLeapYear() ? 1 : 0)",
							o : "(this.getFullYear() + (this.getWeekOfYear() == 1 && this.getMonth() > 0 ? +1 : (this.getWeekOfYear() >= 52 && this.getMonth() < 11 ? -1 : 0)))",
							Y : "this.getFullYear()",
							y : "('' + this.getFullYear()).substring(2, 4)",
							a : "(this.getHours() < 12 ? 'am' : 'pm')",
							A : "(this.getHours() < 12 ? 'AM' : 'PM')",
							g : "((this.getHours() % 12) ? this.getHours() % 12 : 12)",
							G : "this.getHours()",
							h : "String.leftPad((this.getHours() % 12) ? this.getHours() % 12 : 12, 2, '0')",
							H : "String.leftPad(this.getHours(), 2, '0')",
							i : "String.leftPad(this.getMinutes(), 2, '0')",
							s : "String.leftPad(this.getSeconds(), 2, '0')",
							u : "String.leftPad(this.getMilliseconds(), 3, '0')",
							O : "this.getGMTOffset()",
							P : "this.getGMTOffset(true)",
							T : "this.getTimezone()",
							Z : "(this.getTimezoneOffset() * -60)",
							c : function() {
								for ( var k = "Y-m-dTH:i:sP", h = [], g = 0, d = k.length; g < d; ++g) {
									var j = k.charAt(g);
									h.push(j == "T" ? "'T'" : Date.getFormatCode(j))
								}
								return h.join(" + ")
							},
							U : "Math.round(this.getTime() / 1000)"
						},
						isValid : function(o, c, n, k, g, j, e) {
							k = k || 0;
							g = g || 0;
							j = j || 0;
							e = e || 0;
							var l = new Date(o, c - 1, n, k, g, j, e);
							return o == l.getFullYear() && c == l.getMonth() + 1 && n == l.getDate()
									&& k == l.getHours() && g == l.getMinutes() && j == l.getSeconds()
									&& e == l.getMilliseconds()
						},
						parseDate : function(d, g, c) {
							var e = Date.parseFunctions;
							if (e[g] == null) {
								Date.createParser(g)
							}
							return e[g](d, Ext.isDefined(c) ? c : Date.useStrict)
						},
						getFormatCode : function(d) {
							var c = Date.formatCodes[d];
							if (c) {
								c = typeof c == "function" ? c() : c;
								Date.formatCodes[d] = c
							}
							return c || ("'" + String.escape(d) + "'")
						},
						createFormat : function(h) {
							var g = [], c = false, e = "";
							for ( var d = 0; d < h.length; ++d) {
								e = h.charAt(d);
								if (!c && e == "\\") {
									c = true
								} else {
									if (c) {
										c = false;
										g.push("'" + String.escape(e) + "'")
									} else {
										g.push(Date.getFormatCode(e))
									}
								}
							}
							Date.formatFunctions[h] = new Function("return " + g.join("+"))
						},
						createParser : function() {
							var c = [
									"var dt, y, m, d, h, i, s, ms, o, z, zz, u, v,",
									"def = Date.defaults,",
									"results = String(input).match(Date.parseRegexes[{0}]);",
									"if(results){",
									"{1}",
									"if(u != null){",
									"v = new Date(u * 1000);",
									"}else{",
									"dt = (new Date()).clearTime();",
									"y = y >= 0? y : Ext.num(def.y, dt.getFullYear());",
									"m = m >= 0? m : Ext.num(def.m - 1, dt.getMonth());",
									"d = d >= 0? d : Ext.num(def.d, dt.getDate());",
									"h  = h || Ext.num(def.h, dt.getHours());",
									"i  = i || Ext.num(def.i, dt.getMinutes());",
									"s  = s || Ext.num(def.s, dt.getSeconds());",
									"ms = ms || Ext.num(def.ms, dt.getMilliseconds());",
									"if(z >= 0 && y >= 0){",
									"v = new Date(y, 0, 1, h, i, s, ms);",
									"v = !strict? v : (strict === true && (z <= 364 || (v.isLeapYear() && z <= 365))? v.add(Date.DAY, z) : null);",
									"}else if(strict === true && !Date.isValid(y, m + 1, d, h, i, s, ms)){",
									"v = null;",
									"}else{",
									"v = new Date(y, m, d, h, i, s, ms);",
									"}",
									"}",
									"}",
									"if(v){",
									"if(zz != null){",
									"v = v.add(Date.SECOND, -v.getTimezoneOffset() * 60 - zz);",
									"}else if(o){",
									"v = v.add(Date.MINUTE, -v.getTimezoneOffset() + (sn == '+'? -1 : 1) * (hr * 60 + mn));",
									"}", "}", "return v;" ].join("\n");
							return function(m) {
								var e = Date.parseRegexes.length, n = 1, g = [], l = [], k = false, d = "";
								for ( var j = 0; j < m.length; ++j) {
									d = m.charAt(j);
									if (!k && d == "\\") {
										k = true
									} else {
										if (k) {
											k = false;
											l.push(String.escape(d))
										} else {
											var h = a(d, n);
											n += h.g;
											l.push(h.s);
											if (h.g && h.c) {
												g.push(h.c)
											}
										}
									}
								}
								Date.parseRegexes[e] = new RegExp("^" + l.join("") + "$", "i");
								Date.parseFunctions[m] = new Function("input", "strict", b(c, e, g.join("")))
							}
						}(),
						parseCodes : {
							d : {
								g : 1,
								c : "d = parseInt(results[{0}], 10);\n",
								s : "(\\d{2})"
							},
							j : {
								g : 1,
								c : "d = parseInt(results[{0}], 10);\n",
								s : "(\\d{1,2})"
							},
							D : function() {
								for ( var c = [], d = 0; d < 7; c.push(Date.getShortDayName(d)), ++d) {
								}
								return {
									g : 0,
									c : null,
									s : "(?:" + c.join("|") + ")"
								}
							},
							l : function() {
								return {
									g : 0,
									c : null,
									s : "(?:" + Date.dayNames.join("|") + ")"
								}
							},
							N : {
								g : 0,
								c : null,
								s : "[1-7]"
							},
							S : {
								g : 0,
								c : null,
								s : "(?:st|nd|rd|th)"
							},
							w : {
								g : 0,
								c : null,
								s : "[0-6]"
							},
							z : {
								g : 1,
								c : "z = parseInt(results[{0}], 10);\n",
								s : "(\\d{1,3})"
							},
							W : {
								g : 0,
								c : null,
								s : "(?:\\d{2})"
							},
							F : function() {
								return {
									g : 1,
									c : "m = parseInt(Date.getMonthNumber(results[{0}]), 10);\n",
									s : "(" + Date.monthNames.join("|") + ")"
								}
							},
							M : function() {
								for ( var c = [], d = 0; d < 12; c.push(Date.getShortMonthName(d)), ++d) {
								}
								return Ext.applyIf({
									s : "(" + c.join("|") + ")"
								}, a("F"))
							},
							m : {
								g : 1,
								c : "m = parseInt(results[{0}], 10) - 1;\n",
								s : "(\\d{2})"
							},
							n : {
								g : 1,
								c : "m = parseInt(results[{0}], 10) - 1;\n",
								s : "(\\d{1,2})"
							},
							t : {
								g : 0,
								c : null,
								s : "(?:\\d{2})"
							},
							L : {
								g : 0,
								c : null,
								s : "(?:1|0)"
							},
							o : function() {
								return a("Y")
							},
							Y : {
								g : 1,
								c : "y = parseInt(results[{0}], 10);\n",
								s : "(\\d{4})"
							},
							y : {
								g : 1,
								c : "var ty = parseInt(results[{0}], 10);\ny = ty > Date.y2kYear ? 1900 + ty : 2000 + ty;\n",
								s : "(\\d{1,2})"
							},
							a : {
								g : 1,
								c : "if (results[{0}] == 'am') {\nif (h == 12) { h = 0; }\n} else { if (h < 12) { h += 12; }}",
								s : "(am|pm)"
							},
							A : {
								g : 1,
								c : "if (results[{0}] == 'AM') {\nif (h == 12) { h = 0; }\n} else { if (h < 12) { h += 12; }}",
								s : "(AM|PM)"
							},
							g : function() {
								return a("G")
							},
							G : {
								g : 1,
								c : "h = parseInt(results[{0}], 10);\n",
								s : "(\\d{1,2})"
							},
							h : function() {
								return a("H")
							},
							H : {
								g : 1,
								c : "h = parseInt(results[{0}], 10);\n",
								s : "(\\d{2})"
							},
							i : {
								g : 1,
								c : "i = parseInt(results[{0}], 10);\n",
								s : "(\\d{2})"
							},
							s : {
								g : 1,
								c : "s = parseInt(results[{0}], 10);\n",
								s : "(\\d{2})"
							},
							u : {
								g : 1,
								c : "ms = results[{0}]; ms = parseInt(ms, 10)/Math.pow(10, ms.length - 3);\n",
								s : "(\\d+)"
							},
							O : {
								g : 1,
								c : [
										"o = results[{0}];",
										"var sn = o.substring(0,1),",
										"hr = o.substring(1,3)*1 + Math.floor(o.substring(3,5) / 60),",
										"mn = o.substring(3,5) % 60;",
										"o = ((-12 <= (hr*60 + mn)/60) && ((hr*60 + mn)/60 <= 14))? (sn + String.leftPad(hr, 2, '0') + String.leftPad(mn, 2, '0')) : null;\n" ]
										.join("\n"),
								s : "([+-]\\d{4})"
							},
							P : {
								g : 1,
								c : [
										"o = results[{0}];",
										"var sn = o.substring(0,1),",
										"hr = o.substring(1,3)*1 + Math.floor(o.substring(4,6) / 60),",
										"mn = o.substring(4,6) % 60;",
										"o = ((-12 <= (hr*60 + mn)/60) && ((hr*60 + mn)/60 <= 14))? (sn + String.leftPad(hr, 2, '0') + String.leftPad(mn, 2, '0')) : null;\n" ]
										.join("\n"),
								s : "([+-]\\d{2}:\\d{2})"
							},
							T : {
								g : 0,
								c : null,
								s : "[A-Z]{1,4}"
							},
							Z : {
								g : 1,
								c : "zz = results[{0}] * 1;\nzz = (-43200 <= zz && zz <= 50400)? zz : null;\n",
								s : "([+-]?\\d{1,5})"
							},
							c : function() {
								var e = [], c = [
										a("Y", 1),
										a("m", 2),
										a("d", 3),
										a("h", 4),
										a("i", 5),
										a("s", 6),
										{
											c : "ms = results[7] || '0'; ms = parseInt(ms, 10)/Math.pow(10, ms.length - 3);\n"
										},
										{
											c : [ "if(results[8]) {", "if(results[8] == 'Z'){", "zz = 0;",
													"}else if (results[8].indexOf(':') > -1){", a("P", 8).c, "}else{",
													a("O", 8).c, "}", "}" ].join("\n")
										} ];
								for ( var g = 0, d = c.length; g < d; ++g) {
									e.push(c[g].c)
								}
								return {
									g : 1,
									c : e.join(""),
									s : [ c[0].s, "(?:", "-", c[1].s, "(?:", "-", c[2].s, "(?:", "(?:T| )?", c[3].s,
											":", c[4].s, "(?::", c[5].s, ")?", "(?:(?:\\.|,)(\\d+))?",
											"(Z|(?:[-+]\\d{2}(?::)?\\d{2}))?", ")?", ")?", ")?" ].join("")
								}
							},
							U : {
								g : 1,
								c : "u = parseInt(results[{0}], 10);\n",
								s : "(-?\\d+)"
							}
						}
					})
}());
Ext.apply(Date.prototype,
		{
			dateFormat : function(a) {
				if (Date.formatFunctions[a] == null) {
					Date.createFormat(a)
				}
				return Date.formatFunctions[a].call(this)
			},
			getTimezone : function() {
				return this.toString().replace(/^.* (?:\((.*)\)|([A-Z]{1,4})(?:[\-+][0-9]{4})?(?: -?\d+)?)$/, "$1$2")
						.replace(/[^A-Z]/g, "")
			},
			getGMTOffset : function(a) {
				return (this.getTimezoneOffset() > 0 ? "-" : "+")
						+ String.leftPad(Math.floor(Math.abs(this.getTimezoneOffset()) / 60), 2, "0") + (a ? ":" : "")
						+ String.leftPad(Math.abs(this.getTimezoneOffset() % 60), 2, "0")
			},
			getDayOfYear : function() {
				var c = 0, b = 0, e = this.clone(), a = this.getMonth();
				for (c = 0, e.setMonth(0); c < a; e.setMonth(++c)) {
					b += e.getDaysInMonth()
				}
				return b + this.getDate() - 1
			},
			getWeekOfYear : function() {
				var a = 86400000, b = 7 * a;
				return function() {
					var d = Date.UTC(this.getFullYear(), this.getMonth(), this.getDate() + 3) / a, c = Math
							.floor(d / 7), e = new Date(c * b).getUTCFullYear();
					return c - Math.floor(Date.UTC(e, 0, 7) / b) + 1
				}
			}(),
			isLeapYear : function() {
				var a = this.getFullYear();
				return !!((a & 3) == 0 && (a % 100 || (a % 400 == 0 && a)))
			},
			getFirstDayOfMonth : function() {
				var a = (this.getDay() - (this.getDate() - 1)) % 7;
				return (a < 0) ? (a + 7) : a
			},
			getLastDayOfMonth : function() {
				return this.getLastDateOfMonth().getDay()
			},
			getFirstDateOfMonth : function() {
				return new Date(this.getFullYear(), this.getMonth(), 1)
			},
			getLastDateOfMonth : function() {
				return new Date(this.getFullYear(), this.getMonth(), this.getDaysInMonth())
			},
			getDaysInMonth : function() {
				var a = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
				return function() {
					var b = this.getMonth();
					return b == 1 && this.isLeapYear() ? 29 : a[b]
				}
			}(),
			getSuffix : function() {
				switch (this.getDate()) {
				case 1:
				case 21:
				case 31:
					return "st";
				case 2:
				case 22:
					return "nd";
				case 3:
				case 23:
					return "rd";
				default:
					return "th"
				}
			},
			clone : function() {
				return new Date(this.getTime())
			},
			isDST : function() {
				return new Date(this.getFullYear(), 0, 1).getTimezoneOffset() != this.getTimezoneOffset()
			},
			clearTime : function(g) {
				if (g) {
					return this.clone().clearTime()
				}
				var b = this.getDate();
				this.setHours(0);
				this.setMinutes(0);
				this.setSeconds(0);
				this.setMilliseconds(0);
				if (this.getDate() != b) {
					for ( var a = 1, e = this.add(Date.HOUR, a); e.getDate() != b; a++, e = this.add(Date.HOUR, a)) {
					}
					this.setDate(b);
					this.setHours(e.getHours())
				}
				return this
			},
			add : function(b, c) {
				var e = this.clone();
				if (!b || c === 0) {
					return e
				}
				switch (b.toLowerCase()) {
				case Date.MILLI:
					e.setMilliseconds(this.getMilliseconds() + c);
					break;
				case Date.SECOND:
					e.setSeconds(this.getSeconds() + c);
					break;
				case Date.MINUTE:
					e.setMinutes(this.getMinutes() + c);
					break;
				case Date.HOUR:
					e.setHours(this.getHours() + c);
					break;
				case Date.DAY:
					e.setDate(this.getDate() + c);
					break;
				case Date.MONTH:
					var a = this.getDate();
					if (a > 28) {
						a = Math.min(a, this.getFirstDateOfMonth().add("mo", c).getLastDateOfMonth().getDate())
					}
					e.setDate(a);
					e.setMonth(this.getMonth() + c);
					break;
				case Date.YEAR:
					e.setFullYear(this.getFullYear() + c);
					break
				}
				return e
			},
			between : function(c, a) {
				var b = this.getTime();
				return c.getTime() <= b && b <= a.getTime()
			}
		});
Date.prototype.format = Date.prototype.dateFormat;
if (Ext.isSafari && (navigator.userAgent.match(/WebKit\/(\d+)/)[1] || NaN) < 420) {
	Ext.apply(Date.prototype, {
		_xMonth : Date.prototype.setMonth,
		_xDate : Date.prototype.setDate,
		setMonth : function(a) {
			if (a <= -1) {
				var d = Math.ceil(-a), c = Math.ceil(d / 12), b = (d % 12) ? 12 - d % 12 : 0;
				this.setFullYear(this.getFullYear() - c);
				return this._xMonth(b)
			} else {
				return this._xMonth(a)
			}
		},
		setDate : function(a) {
			return this.setTime(this.getTime() - (this.getDate() - a) * 86400000)
		}
	})
}
Ext.util.DelayedTask = function(d, c, a) {
	var e = this, g, b = function() {
		clearInterval(g);
		g = null;
		d.apply(c, a || [])
	};
	e.delay = function(i, k, j, h) {
		e.cancel();
		d = k || d;
		c = j || c;
		a = h || a;
		g = setInterval(b, i)
	};
	e.cancel = function() {
		if (g) {
			clearInterval(g);
			g = null
		}
	}
};
Ext.util.MixedCollection = function(b, a) {
	this.items = [];
	this.map = {};
	this.keys = [];
	this.length = 0;
	this.addEvents("clear", "add", "replace", "remove", "sort");
	this.allowFunctions = b === true;
	if (a) {
		this.getKey = a
	}
	Ext.util.MixedCollection.superclass.constructor.call(this)
};
Ext.extend(Ext.util.MixedCollection, Ext.util.Observable, {
	allowFunctions : false,
	add : function(b, c) {
		if (arguments.length == 1) {
			c = arguments[0];
			b = this.getKey(c)
		}
		if (typeof b != "undefined" && b !== null) {
			var a = this.map[b];
			if (typeof a != "undefined") {
				return this.replace(b, c)
			}
			this.map[b] = c
		}
		this.length++;
		this.items.push(c);
		this.keys.push(b);
		this.fireEvent("add", this.length - 1, c, b);
		return c
	},
	getKey : function(a) {
		return a.id
	},
	replace : function(c, d) {
		if (arguments.length == 1) {
			d = arguments[0];
			c = this.getKey(d)
		}
		var a = this.map[c];
		if (typeof c == "undefined" || c === null || typeof a == "undefined") {
			return this.add(c, d)
		}
		var b = this.indexOfKey(c);
		this.items[b] = d;
		this.map[c] = d;
		this.fireEvent("replace", c, a, d);
		return d
	},
	addAll : function(e) {
		if (arguments.length > 1 || Ext.isArray(e)) {
			var b = arguments.length > 1 ? arguments : e;
			for ( var d = 0, a = b.length; d < a; d++) {
				this.add(b[d])
			}
		} else {
			for ( var c in e) {
				if (this.allowFunctions || typeof e[c] != "function") {
					this.add(c, e[c])
				}
			}
		}
	},
	each : function(e, d) {
		var b = [].concat(this.items);
		for ( var c = 0, a = b.length; c < a; c++) {
			if (e.call(d || b[c], b[c], c, a) === false) {
				break
			}
		}
	},
	eachKey : function(d, c) {
		for ( var b = 0, a = this.keys.length; b < a; b++) {
			d.call(c || window, this.keys[b], this.items[b], b, a)
		}
	},
	find : function(d, c) {
		for ( var b = 0, a = this.items.length; b < a; b++) {
			if (d.call(c || window, this.items[b], this.keys[b])) {
				return this.items[b]
			}
		}
		return null
	},
	insert : function(a, b, c) {
		if (arguments.length == 2) {
			c = arguments[1];
			b = this.getKey(c)
		}
		if (this.containsKey(b)) {
			this.suspendEvents();
			this.removeKey(b);
			this.resumeEvents()
		}
		if (a >= this.length) {
			return this.add(b, c)
		}
		this.length++;
		this.items.splice(a, 0, c);
		if (typeof b != "undefined" && b !== null) {
			this.map[b] = c
		}
		this.keys.splice(a, 0, b);
		this.fireEvent("add", a, c, b);
		return c
	},
	remove : function(a) {
		return this.removeAt(this.indexOf(a))
	},
	removeAt : function(a) {
		if (a < this.length && a >= 0) {
			this.length--;
			var c = this.items[a];
			this.items.splice(a, 1);
			var b = this.keys[a];
			if (typeof b != "undefined") {
				delete this.map[b]
			}
			this.keys.splice(a, 1);
			this.fireEvent("remove", c, b);
			return c
		}
		return false
	},
	removeKey : function(a) {
		return this.removeAt(this.indexOfKey(a))
	},
	getCount : function() {
		return this.length
	},
	indexOf : function(a) {
		return this.items.indexOf(a)
	},
	indexOfKey : function(a) {
		return this.keys.indexOf(a)
	},
	item : function(b) {
		var a = this.map[b], c = a !== undefined ? a : (typeof b == "number") ? this.items[b] : undefined;
		return !Ext.isFunction(c) || this.allowFunctions ? c : null
	},
	itemAt : function(a) {
		return this.items[a]
	},
	key : function(a) {
		return this.map[a]
	},
	contains : function(a) {
		return this.indexOf(a) != -1
	},
	containsKey : function(a) {
		return typeof this.map[a] != "undefined"
	},
	clear : function() {
		this.length = 0;
		this.items = [];
		this.keys = [];
		this.map = {};
		this.fireEvent("clear")
	},
	first : function() {
		return this.items[0]
	},
	last : function() {
		return this.items[this.length - 1]
	},
	_sort : function(m, a, l) {
		var e, g, d = String(a).toUpperCase() == "DESC" ? -1 : 1, j = [], b = this.keys, h = this.items;
		l = l || function(i, c) {
			return i - c
		};
		for (e = 0, g = h.length; e < g; e++) {
			j[j.length] = {
				key : b[e],
				value : h[e],
				index : e
			}
		}
		j.sort(function(i, c) {
			var k = l(i[m], c[m]) * d;
			if (k === 0) {
				k = (i.index < c.index ? -1 : 1)
			}
			return k
		});
		for (e = 0, g = j.length; e < g; e++) {
			h[e] = j[e].value;
			b[e] = j[e].key
		}
		this.fireEvent("sort", this)
	},
	sort : function(a, b) {
		this._sort("value", a, b)
	},
	keySort : function(a, b) {
		this._sort("key", a, b || function(d, c) {
			var g = String(d).toUpperCase(), e = String(c).toUpperCase();
			return g > e ? 1 : (g < e ? -1 : 0)
		})
	},
	getRange : function(e, a) {
		var b = this.items;
		if (b.length < 1) {
			return []
		}
		e = e || 0;
		a = Math.min(typeof a == "undefined" ? this.length - 1 : a, this.length - 1);
		var c, d = [];
		if (e <= a) {
			for (c = e; c <= a; c++) {
				d[d.length] = b[c]
			}
		} else {
			for (c = e; c >= a; c--) {
				d[d.length] = b[c]
			}
		}
		return d
	},
	filter : function(c, b, d, a) {
		if (Ext.isEmpty(b, false)) {
			return this.clone()
		}
		b = this.createValueMatcher(b, d, a);
		return this.filterBy(function(e) {
			return e && b.test(e[c])
		})
	},
	filterBy : function(g, e) {
		var h = new Ext.util.MixedCollection();
		h.getKey = this.getKey;
		var b = this.keys, d = this.items;
		for ( var c = 0, a = d.length; c < a; c++) {
			if (g.call(e || this, d[c], b[c])) {
				h.add(b[c], d[c])
			}
		}
		return h
	},
	findIndex : function(c, b, e, d, a) {
		if (Ext.isEmpty(b, false)) {
			return -1
		}
		b = this.createValueMatcher(b, d, a);
		return this.findIndexBy(function(g) {
			return g && b.test(g[c])
		}, null, e)
	},
	findIndexBy : function(g, e, h) {
		var b = this.keys, d = this.items;
		for ( var c = (h || 0), a = d.length; c < a; c++) {
			if (g.call(e || this, d[c], b[c])) {
				return c
			}
		}
		return -1
	},
	createValueMatcher : function(b, c, a) {
		if (!b.exec) {
			b = String(b);
			b = new RegExp((c === true ? "" : "^") + Ext.escapeRe(b), a ? "" : "i")
		}
		return b
	},
	clone : function() {
		var e = new Ext.util.MixedCollection();
		var b = this.keys, d = this.items;
		for ( var c = 0, a = d.length; c < a; c++) {
			e.add(b[c], d[c])
		}
		e.getKey = this.getKey;
		return e
	}
});
Ext.util.MixedCollection.prototype.get = Ext.util.MixedCollection.prototype.item;
Ext.util.JSON = new (function() {
	var useHasOwn = !!{}.hasOwnProperty, isNative = function() {
		var useNative = null;
		return function() {
			if (useNative === null) {
				useNative = Ext.USE_NATIVE_JSON && window.JSON && JSON.toString() == "[object JSON]"
			}
			return useNative
		}
	}(), pad = function(n) {
		return n < 10 ? "0" + n : n
	}, doDecode = function(json) {
		return eval("(" + json + ")")
	}, doEncode = function(o) {
		if (typeof o == "undefined" || o === null) {
			return "null"
		} else {
			if (Ext.isArray(o)) {
				return encodeArray(o)
			} else {
				if (Object.prototype.toString.apply(o) === "[object Date]") {
					return Ext.util.JSON.encodeDate(o)
				} else {
					if (typeof o == "string") {
						return encodeString(o)
					} else {
						if (typeof o == "number") {
							return isFinite(o) ? String(o) : "null"
						} else {
							if (typeof o == "boolean") {
								return String(o)
							} else {
								var a = [ "{" ], b, i, v;
								for (i in o) {
									if (!useHasOwn || o.hasOwnProperty(i)) {
										v = o[i];
										switch (typeof v) {
										case "undefined":
										case "function":
										case "unknown":
											break;
										default:
											if (b) {
												a.push(",")
											}
											a.push(doEncode(i), ":", v === null ? "null" : doEncode(v));
											b = true
										}
									}
								}
								a.push("}");
								return a.join("")
							}
						}
					}
				}
			}
		}
	}, m = {
		"\b" : "\\b",
		"\t" : "\\t",
		"\n" : "\\n",
		"\f" : "\\f",
		"\r" : "\\r",
		'"' : '\\"',
		"\\" : "\\\\"
	}, encodeString = function(s) {
		if (/["\\\x00-\x1f]/.test(s)) {
			return '"' + s.replace(/([\x00-\x1f\\"])/g, function(a, b) {
				var c = m[b];
				if (c) {
					return c
				}
				c = b.charCodeAt();
				return "\\u00" + Math.floor(c / 16).toString(16) + (c % 16).toString(16)
			}) + '"'
		}
		return '"' + s + '"'
	}, encodeArray = function(o) {
		var a = [ "[" ], b, i, l = o.length, v;
		for (i = 0; i < l; i += 1) {
			v = o[i];
			switch (typeof v) {
			case "undefined":
			case "function":
			case "unknown":
				break;
			default:
				if (b) {
					a.push(",")
				}
				a.push(v === null ? "null" : Ext.util.JSON.encode(v));
				b = true
			}
		}
		a.push("]");
		return a.join("")
	};
	this.encodeDate = function(o) {
		return '"' + o.getFullYear() + "-" + pad(o.getMonth() + 1) + "-" + pad(o.getDate()) + "T" + pad(o.getHours())
				+ ":" + pad(o.getMinutes()) + ":" + pad(o.getSeconds()) + '"'
	};
	this.encode = function() {
		var ec;
		return function(o) {
			if (!ec) {
				ec = isNative() ? JSON.stringify : doEncode
			}
			return ec(o)
		}
	}();
	this.decode = function() {
		var dc;
		return function(json) {
			if (!dc) {
				dc = isNative() ? JSON.parse : doDecode
			}
			return dc(json)
		}
	}()
})();
Ext.encode = Ext.util.JSON.encode;
Ext.decode = Ext.util.JSON.decode;
Ext.util.Format = function() {
	var trimRe = /^\s+|\s+$/g;
	return {
		ellipsis : function(value, len, word) {
			if (value && value.length > len) {
				if (word) {
					var vs = value.substr(0, len - 2);
					var index = Math.max(vs.lastIndexOf(" "), vs.lastIndexOf("."), vs.lastIndexOf("!"), vs
							.lastIndexOf("?"));
					if (index == -1 || index < (len - 15)) {
						return value.substr(0, len - 3) + "..."
					} else {
						return vs.substr(0, index) + "..."
					}
				} else {
					return value.substr(0, len - 3) + "..."
				}
			}
			return value
		},
		undef : function(value) {
			return value !== undefined ? value : ""
		},
		defaultValue : function(value, defaultValue) {
			return value !== undefined && value !== "" ? value : defaultValue
		},
		htmlEncode : function(value) {
			return !value ? value : String(value).replace(/&/g, "&amp;").replace(/>/g, "&gt;").replace(/</g, "&lt;")
					.replace(/"/g, "&quot;")
		},
		htmlDecode : function(value) {
			return !value ? value : String(value).replace(/&gt;/g, ">").replace(/&lt;/g, "<").replace(/&quot;/g, '"')
					.replace(/&amp;/g, "&")
		},
		trim : function(value) {
			return String(value).replace(trimRe, "")
		},
		substr : function(value, start, length) {
			return String(value).substr(start, length)
		},
		lowercase : function(value) {
			return String(value).toLowerCase()
		},
		uppercase : function(value) {
			return String(value).toUpperCase()
		},
		capitalize : function(value) {
			return !value ? value : value.charAt(0).toUpperCase() + value.substr(1).toLowerCase()
		},
		call : function(value, fn) {
			if (arguments.length > 2) {
				var args = Array.prototype.slice.call(arguments, 2);
				args.unshift(value);
				return eval(fn).apply(window, args)
			} else {
				return eval(fn).call(window, value)
			}
		},
		usMoney : function(v) {
			v = (Math.round((v - 0) * 100)) / 100;
			v = (v == Math.floor(v)) ? v + ".00" : ((v * 10 == Math.floor(v * 10)) ? v + "0" : v);
			v = String(v);
			var ps = v.split(".");
			var whole = ps[0];
			var sub = ps[1] ? "." + ps[1] : ".00";
			var r = /(\d+)(\d{3})/;
			while (r.test(whole)) {
				whole = whole.replace(r, "$1,$2")
			}
			v = whole + sub;
			if (v.charAt(0) == "-") {
				return "-$" + v.substr(1)
			}
			return "$" + v
		},
		date : function(v, format) {
			if (!v) {
				return ""
			}
			if (!Ext.isDate(v)) {
				v = new Date(Date.parse(v))
			}
			return v.dateFormat(format || "m/d/Y")
		},
		dateRenderer : function(format) {
			return function(v) {
				return Ext.util.Format.date(v, format)
			}
		},
		stripTagsRE : /<\/?[^>]+>/gi,
		stripTags : function(v) {
			return !v ? v : String(v).replace(this.stripTagsRE, "")
		},
		stripScriptsRe : /(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)/ig,
		stripScripts : function(v) {
			return !v ? v : String(v).replace(this.stripScriptsRe, "")
		},
		fileSize : function(size) {
			if (size < 1024) {
				return size + " bytes"
			} else {
				if (size < 1048576) {
					return (Math.round(((size * 10) / 1024)) / 10) + " KB"
				} else {
					return (Math.round(((size * 10) / 1048576)) / 10) + " MB"
				}
			}
		},
		math : function() {
			var fns = {};
			return function(v, a) {
				if (!fns[a]) {
					fns[a] = new Function("v", "return v " + a + ";")
				}
				return fns[a](v)
			}
		}(),
		round : function(value, precision) {
			var result = Number(value);
			if (typeof precision == "number") {
				precision = Math.pow(10, precision);
				result = Math.round(value * precision) / precision
			}
			return result
		},
		number : function(v, format) {
			if (!format) {
				return v
			}
			v = Ext.num(v, NaN);
			if (isNaN(v)) {
				return ""
			}
			var comma = ",", dec = ".", i18n = false, neg = v < 0;
			v = Math.abs(v);
			if (format.substr(format.length - 2) == "/i") {
				format = format.substr(0, format.length - 2);
				i18n = true;
				comma = ".";
				dec = ","
			}
			var hasComma = format.indexOf(comma) != -1, psplit = (i18n ? format.replace(/[^\d\,]/g, "") : format
					.replace(/[^\d\.]/g, "")).split(dec);
			if (1 < psplit.length) {
				v = v.toFixed(psplit[1].length)
			} else {
				if (2 < psplit.length) {
					throw ("NumberFormatException: invalid format, formats should have no more than 1 period: " + format)
				} else {
					v = v.toFixed(0)
				}
			}
			var fnum = v.toString();
			if (hasComma) {
				psplit = fnum.split(".");
				var cnum = psplit[0], parr = [], j = cnum.length, m = Math.floor(j / 3), n = cnum.length % 3 || 3;
				for ( var i = 0; i < j; i += n) {
					if (i != 0) {
						n = 3
					}
					parr[parr.length] = cnum.substr(i, n);
					m -= 1
				}
				fnum = parr.join(comma);
				if (psplit[1]) {
					fnum += dec + psplit[1]
				}
			}
			return (neg ? "-" : "") + format.replace(/[\d,?\.?]+/, fnum)
		},
		numberRenderer : function(format) {
			return function(v) {
				return Ext.util.Format.number(v, format)
			}
		},
		plural : function(v, s, p) {
			return v + " " + (v == 1 ? s : (p ? p : s + "s"))
		},
		nl2br : function(v) {
			return v === undefined || v === null ? "" : v.replace(/\n/g, "<br/>")
		}
	}
}();
Ext.XTemplate = function() {
	Ext.XTemplate.superclass.constructor.apply(this, arguments);
	var w = this, i = w.html, p = /<tpl\b[^>]*>((?:(?=([^<]+))\2|<(?!tpl\b[^>]*>))*?)<\/tpl>/, d = /^<tpl\b[^>]*?for="(.*?)"/, t = /^<tpl\b[^>]*?if="(.*?)"/, v = /^<tpl\b[^>]*?exec="(.*?)"/, q, o = 0, j = [], n = "values", u = "parent", k = "xindex", l = "xcount", e = "return ", c = "with(values){ ";
	i = [ "<tpl>", i, "</tpl>" ].join("");
	while ((q = i.match(p))) {
		var b = q[0].match(d), a = q[0].match(t), y = q[0].match(v), g = null, h = null, r = null, x = b && b[1] ? b[1]
				: "";
		if (a) {
			g = a && a[1] ? a[1] : null;
			if (g) {
				h = new Function(n, u, k, l, c + e + (Ext.util.Format.htmlDecode(g)) + "; }")
			}
		}
		if (y) {
			g = y && y[1] ? y[1] : null;
			if (g) {
				r = new Function(n, u, k, l, c + (Ext.util.Format.htmlDecode(g)) + "; }")
			}
		}
		if (x) {
			switch (x) {
			case ".":
				x = new Function(n, u, c + e + n + "; }");
				break;
			case "..":
				x = new Function(n, u, c + e + u + "; }");
				break;
			default:
				x = new Function(n, u, c + e + x + "; }")
			}
		}
		j.push({
			id : o,
			target : x,
			exec : r,
			test : h,
			body : q[1] || ""
		});
		i = i.replace(q[0], "{xtpl" + o + "}");
		++o
	}
	Ext.each(j, function(m) {
		w.compileTpl(m)
	});
	w.master = j[j.length - 1];
	w.tpls = j
};
Ext.extend(Ext.XTemplate, Ext.Template, {
	re : /\{([\w-\.\#]+)(?:\:([\w\.]*)(?:\((.*?)?\))?)?(\s?[\+\-\*\\]\s?[\d\.\+\-\*\\\(\)]+)?\}/g,
	codeRe : /\{\[((?:\\\]|.|\n)*?)\]\}/g,
	applySubTemplate : function(a, i, h, d, c) {
		var g = this, e, k = g.tpls[a], j, b = [];
		if ((k.test && !k.test.call(g, i, h, d, c)) || (k.exec && k.exec.call(g, i, h, d, c))) {
			return ""
		}
		j = k.target ? k.target.call(g, i, h) : i;
		e = j.length;
		h = k.target ? i : h;
		if (k.target && Ext.isArray(j)) {
			Ext.each(j, function(l, m) {
				b[b.length] = k.compiled.call(g, l, h, m + 1, e)
			});
			return b.join("")
		}
		return k.compiled.call(g, j, h, d, c)
	},
	compileTpl : function(tpl) {
		var fm = Ext.util.Format, useF = this.disableFormats !== true, sep = Ext.isGecko ? "+" : ",", body;
		function fn(m, name, format, args, math) {
			if (name.substr(0, 4) == "xtpl") {
				return "'" + sep + "this.applySubTemplate(" + name.substr(4) + ", values, parent, xindex, xcount)"
						+ sep + "'"
			}
			var v;
			if (name === ".") {
				v = "values"
			} else {
				if (name === "#") {
					v = "xindex"
				} else {
					if (name.indexOf(".") != -1) {
						v = name
					} else {
						v = "values['" + name + "']"
					}
				}
			}
			if (math) {
				v = "(" + v + math + ")"
			}
			if (format && useF) {
				args = args ? "," + args : "";
				if (format.substr(0, 5) != "this.") {
					format = "fm." + format + "("
				} else {
					format = 'this.call("' + format.substr(5) + '", ';
					args = ", values"
				}
			} else {
				args = "";
				format = "(" + v + " === undefined ? '' : "
			}
			return "'" + sep + format + v + args + ")" + sep + "'"
		}
		function codeFn(m, code) {
			return "'" + sep + "(" + code + ")" + sep + "'"
		}
		if (Ext.isGecko) {
			body = "tpl.compiled = function(values, parent, xindex, xcount){ return '"
					+ tpl.body.replace(/(\r\n|\n)/g, "\\n").replace(/'/g, "\\'").replace(this.re, fn).replace(
							this.codeRe, codeFn) + "';};"
		} else {
			body = [ "tpl.compiled = function(values, parent, xindex, xcount){ return ['" ];
			body.push(tpl.body.replace(/(\r\n|\n)/g, "\\n").replace(/'/g, "\\'").replace(this.re, fn).replace(
					this.codeRe, codeFn));
			body.push("'].join('');};");
			body = body.join("")
		}
		eval(body);
		return this
	},
	applyTemplate : function(a) {
		return this.master.compiled.call(this, a, {}, 1, 1)
	},
	compile : function() {
		return this
	}
});
Ext.XTemplate.prototype.apply = Ext.XTemplate.prototype.applyTemplate;
Ext.XTemplate.from = function(a) {
	a = Ext.getDom(a);
	return new Ext.XTemplate(a.value || a.innerHTML)
};
Ext.util.CSS = function() {
	var d = null;
	var c = document;
	var b = /(-[a-z])/gi;
	var a = function(e, g) {
		return g.charAt(1).toUpperCase()
	};
	return {
		createStyleSheet : function(i, l) {
			var h;
			var g = c.getElementsByTagName("head")[0];
			var k = c.createElement("style");
			k.setAttribute("type", "text/css");
			if (l) {
				k.setAttribute("id", l)
			}
			if (Ext.isIE) {
				g.appendChild(k);
				h = k.styleSheet;
				h.cssText = i
			} else {
				try {
					k.appendChild(c.createTextNode(i))
				} catch (j) {
					k.cssText = i
				}
				g.appendChild(k);
				h = k.styleSheet ? k.styleSheet : (k.sheet || c.styleSheets[c.styleSheets.length - 1])
			}
			this.cacheStyleSheet(h);
			return h
		},
		removeStyleSheet : function(g) {
			var e = c.getElementById(g);
			if (e) {
				e.parentNode.removeChild(e)
			}
		},
		swapStyleSheet : function(h, e) {
			this.removeStyleSheet(h);
			var g = c.createElement("link");
			g.setAttribute("rel", "stylesheet");
			g.setAttribute("type", "text/css");
			g.setAttribute("id", h);
			g.setAttribute("href", e);
			c.getElementsByTagName("head")[0].appendChild(g)
		},
		refreshCache : function() {
			return this.getRules(true)
		},
		cacheStyleSheet : function(h) {
			if (!d) {
				d = {}
			}
			try {
				var k = h.cssRules || h.rules;
				for ( var g = k.length - 1; g >= 0; --g) {
					d[k[g].selectorText] = k[g]
				}
			} catch (i) {
			}
		},
		getRules : function(h) {
			if (d === null || h) {
				d = {};
				var k = c.styleSheets;
				for ( var j = 0, g = k.length; j < g; j++) {
					try {
						this.cacheStyleSheet(k[j])
					} catch (l) {
					}
				}
			}
			return d
		},
		getRule : function(e, h) {
			var g = this.getRules(h);
			if (!Ext.isArray(e)) {
				return g[e]
			}
			for ( var j = 0; j < e.length; j++) {
				if (g[e[j]]) {
					return g[e[j]]
				}
			}
			return null
		},
		updateRule : function(e, j, h) {
			if (!Ext.isArray(e)) {
				var k = this.getRule(e);
				if (k) {
					k.style[j.replace(b, a)] = h;
					return true
				}
			} else {
				for ( var g = 0; g < e.length; g++) {
					if (this.updateRule(e[g], j, h)) {
						return true
					}
				}
			}
			return false
		}
	}
}();
Ext.util.ClickRepeater = function(b, a) {
	this.el = Ext.get(b);
	this.el.unselectable();
	Ext.apply(this, a);
	this.addEvents("mousedown", "click", "mouseup");
	if (!this.disabled) {
		this.disabled = true;
		this.enable()
	}
	if (this.handler) {
		this.on("click", this.handler, this.scope || this)
	}
	Ext.util.ClickRepeater.superclass.constructor.call(this)
};
Ext.extend(Ext.util.ClickRepeater, Ext.util.Observable, {
	interval : 20,
	delay : 250,
	preventDefault : true,
	stopDefault : false,
	timer : 0,
	enable : function() {
		if (this.disabled) {
			this.el.on("mousedown", this.handleMouseDown, this);
			if (this.preventDefault || this.stopDefault) {
				this.el.on("click", this.eventOptions, this)
			}
		}
		this.disabled = false
	},
	disable : function(a) {
		if (a || !this.disabled) {
			clearTimeout(this.timer);
			if (this.pressClass) {
				this.el.removeClass(this.pressClass)
			}
			Ext.getDoc().un("mouseup", this.handleMouseUp, this);
			this.el.removeAllListeners()
		}
		this.disabled = true
	},
	setDisabled : function(a) {
		this[a ? "disable" : "enable"]()
	},
	eventOptions : function(a) {
		if (this.preventDefault) {
			a.preventDefault()
		}
		if (this.stopDefault) {
			a.stopEvent()
		}
	},
	destroy : function() {
		this.disable(true);
		Ext.destroy(this.el);
		this.purgeListeners()
	},
	handleMouseDown : function() {
		clearTimeout(this.timer);
		this.el.blur();
		if (this.pressClass) {
			this.el.addClass(this.pressClass)
		}
		this.mousedownTime = new Date();
		Ext.getDoc().on("mouseup", this.handleMouseUp, this);
		this.el.on("mouseout", this.handleMouseOut, this);
		this.fireEvent("mousedown", this);
		this.fireEvent("click", this);
		if (this.accelerate) {
			this.delay = 400
		}
		this.timer = this.click.defer(this.delay || this.interval, this)
	},
	click : function() {
		this.fireEvent("click", this);
		this.timer = this.click.defer(this.accelerate ? this.easeOutExpo(this.mousedownTime.getElapsed(), 400, -390,
				12000) : this.interval, this)
	},
	easeOutExpo : function(e, a, h, g) {
		return (e == g) ? a + h : h * (-Math.pow(2, -10 * e / g) + 1) + a
	},
	handleMouseOut : function() {
		clearTimeout(this.timer);
		if (this.pressClass) {
			this.el.removeClass(this.pressClass)
		}
		this.el.on("mouseover", this.handleMouseReturn, this)
	},
	handleMouseReturn : function() {
		this.el.un("mouseover", this.handleMouseReturn, this);
		if (this.pressClass) {
			this.el.addClass(this.pressClass)
		}
		this.click()
	},
	handleMouseUp : function() {
		clearTimeout(this.timer);
		this.el.un("mouseover", this.handleMouseReturn, this);
		this.el.un("mouseout", this.handleMouseOut, this);
		Ext.getDoc().un("mouseup", this.handleMouseUp, this);
		this.el.removeClass(this.pressClass);
		this.fireEvent("mouseup", this)
	}
});
Ext.KeyNav = function(b, a) {
	this.el = Ext.get(b);
	Ext.apply(this, a);
	if (!this.disabled) {
		this.disabled = true;
		this.enable()
	}
};
Ext.KeyNav.prototype = {
	disabled : false,
	defaultEventAction : "stopEvent",
	forceKeyDown : false,
	prepareEvent : function(c) {
		var a = c.getKey();
		var b = this.keyToHandler[a];
		if (Ext.isSafari2 && b && a >= 37 && a <= 40) {
			c.stopEvent()
		}
	},
	relay : function(c) {
		var a = c.getKey();
		var b = this.keyToHandler[a];
		if (b && this[b]) {
			if (this.doRelay(c, this[b], b) !== true) {
				c[this.defaultEventAction]()
			}
		}
	},
	doRelay : function(c, b, a) {
		return b.call(this.scope || this, c)
	},
	enter : false,
	left : false,
	right : false,
	up : false,
	down : false,
	tab : false,
	esc : false,
	pageUp : false,
	pageDown : false,
	del : false,
	home : false,
	end : false,
	keyToHandler : {
		37 : "left",
		39 : "right",
		38 : "up",
		40 : "down",
		33 : "pageUp",
		34 : "pageDown",
		46 : "del",
		36 : "home",
		35 : "end",
		13 : "enter",
		27 : "esc",
		9 : "tab"
	},
	enable : function() {
		if (this.disabled) {
			if (this.isKeydown()) {
				this.el.on("keydown", this.relay, this)
			} else {
				this.el.on("keydown", this.prepareEvent, this);
				this.el.on("keypress", this.relay, this)
			}
			this.disabled = false
		}
	},
	disable : function() {
		if (!this.disabled) {
			if (this.isKeydown()) {
				this.el.un("keydown", this.relay, this)
			} else {
				this.el.un("keydown", this.prepareEvent, this);
				this.el.un("keypress", this.relay, this)
			}
			this.disabled = true
		}
	},
	setDisabled : function(a) {
		this[a ? "disable" : "enable"]()
	},
	isKeydown : function() {
		return this.forceKeyDown || Ext.EventManager.useKeydown
	}
};
Ext.KeyMap = function(c, b, a) {
	this.el = Ext.get(c);
	this.eventName = a || "keydown";
	this.bindings = [];
	if (b) {
		this.addBinding(b)
	}
	this.enable()
};
Ext.KeyMap.prototype = {
	stopEvent : false,
	addBinding : function(b) {
		if (Ext.isArray(b)) {
			Ext.each(b, function(j) {
				this.addBinding(j)
			}, this);
			return
		}
		var k = b.key, g = b.fn || b.handler, l = b.scope;
		if (b.stopEvent) {
			this.stopEvent = b.stopEvent
		}
		if (typeof k == "string") {
			var h = [];
			var e = k.toUpperCase();
			for ( var c = 0, d = e.length; c < d; c++) {
				h.push(e.charCodeAt(c))
			}
			k = h
		}
		var a = Ext.isArray(k);
		var i = function(o) {
			if (this.checkModifiers(b, o)) {
				var m = o.getKey();
				if (a) {
					for ( var n = 0, j = k.length; n < j; n++) {
						if (k[n] == m) {
							if (this.stopEvent) {
								o.stopEvent()
							}
							g.call(l || window, m, o);
							return
						}
					}
				} else {
					if (m == k) {
						if (this.stopEvent) {
							o.stopEvent()
						}
						g.call(l || window, m, o)
					}
				}
			}
		};
		this.bindings.push(i)
	},
	checkModifiers : function(b, h) {
		var j, d, g = [ "shift", "ctrl", "alt" ];
		for ( var c = 0, a = g.length; c < a; ++c) {
			d = g[c];
			j = b[d];
			if (!(j === undefined || (j === h[d + "Key"]))) {
				return false
			}
		}
		return true
	},
	on : function(b, d, c) {
		var h, a, e, g;
		if (typeof b == "object" && !Ext.isArray(b)) {
			h = b.key;
			a = b.shift;
			e = b.ctrl;
			g = b.alt
		} else {
			h = b
		}
		this.addBinding({
			key : h,
			shift : a,
			ctrl : e,
			alt : g,
			fn : d,
			scope : c
		})
	},
	handleKeyDown : function(g) {
		if (this.enabled) {
			var c = this.bindings;
			for ( var d = 0, a = c.length; d < a; d++) {
				c[d].call(this, g)
			}
		}
	},
	isEnabled : function() {
		return this.enabled
	},
	enable : function() {
		if (!this.enabled) {
			this.el.on(this.eventName, this.handleKeyDown, this);
			this.enabled = true
		}
	},
	disable : function() {
		if (this.enabled) {
			this.el.removeListener(this.eventName, this.handleKeyDown, this);
			this.enabled = false
		}
	},
	setDisabled : function(a) {
		this[a ? "disable" : "enable"]()
	}
};
Ext.util.TextMetrics = function() {
	var a;
	return {
		measure : function(b, c, d) {
			if (!a) {
				a = Ext.util.TextMetrics.Instance(b, d)
			}
			a.bind(b);
			a.setFixedWidth(d || "auto");
			return a.getSize(c)
		},
		createInstance : function(b, c) {
			return Ext.util.TextMetrics.Instance(b, c)
		}
	}
}();
Ext.util.TextMetrics.Instance = function(b, d) {
	var c = new Ext.Element(document.createElement("div"));
	document.body.appendChild(c.dom);
	c.position("absolute");
	c.setLeftTop(-1000, -1000);
	c.hide();
	if (d) {
		c.setWidth(d)
	}
	var a = {
		getSize : function(g) {
			c.update(g);
			var e = c.getSize();
			c.update("");
			return e
		},
		bind : function(e) {
			c.setStyle(Ext.fly(e).getStyles("font-size", "font-style", "font-weight", "font-family", "line-height",
					"text-transform", "letter-spacing"))
		},
		setFixedWidth : function(e) {
			c.setWidth(e)
		},
		getWidth : function(e) {
			c.dom.style.width = "auto";
			return this.getSize(e).width
		},
		getHeight : function(e) {
			return this.getSize(e).height
		}
	};
	a.bind(b);
	return a
};
Ext.Element.addMethods({
	getTextWidth : function(c, b, a) {
		return (Ext.util.TextMetrics.measure(this.dom, Ext.value(c, this.dom.innerHTML, true)).width).constrain(b || 0,
				a || 1000000)
	}
});
Ext.util.Cookies = {
	set : function(c, e) {
		var a = arguments;
		var i = arguments.length;
		var b = (i > 2) ? a[2] : null;
		var h = (i > 3) ? a[3] : "/";
		var d = (i > 4) ? a[4] : null;
		var g = (i > 5) ? a[5] : false;
		document.cookie = c + "=" + escape(e) + ((b === null) ? "" : ("; expires=" + b.toGMTString()))
				+ ((h === null) ? "" : ("; path=" + h)) + ((d === null) ? "" : ("; domain=" + d))
				+ ((g === true) ? "; secure" : "")
	},
	get : function(d) {
		var b = d + "=";
		var g = b.length;
		var a = document.cookie.length;
		var e = 0;
		var c = 0;
		while (e < a) {
			c = e + g;
			if (document.cookie.substring(e, c) == b) {
				return Ext.util.Cookies.getCookieVal(c)
			}
			e = document.cookie.indexOf(" ", e) + 1;
			if (e === 0) {
				break
			}
		}
		return null
	},
	clear : function(a) {
		if (Ext.util.Cookies.get(a)) {
			document.cookie = a + "=; expires=Thu, 01-Jan-70 00:00:01 GMT"
		}
	},
	getCookieVal : function(b) {
		var a = document.cookie.indexOf(";", b);
		if (a == -1) {
			a = document.cookie.length
		}
		return unescape(document.cookie.substring(b, a))
	}
};
Ext.handleError = function(a) {
	throw a
};
Ext.Error = function(a) {
	this.message = (this.lang[a]) ? this.lang[a] : a
};
Ext.Error.prototype = new Error();
Ext.apply(Ext.Error.prototype, {
	lang : {},
	name : "Ext.Error",
	getName : function() {
		return this.name
	},
	getMessage : function() {
		return this.message
	},
	toJson : function() {
		return Ext.encode(this)
	}
});

Ext.ComponentMgr = function() {
	var c = new Ext.util.MixedCollection();
	var b = {};
	var a = {};
	return {
		register : function(d) {
			c.add(d)
		},
		unregister : function(d) {
			c.remove(d)
		},
		get : function(d) {
			return c.get(d)
		},
		onAvailable : function(f, e, d) {
			c.on("add", function(g, h) {
				if (h.id == f) {
					e.call(d || h, h);
					c.un("add", e, d)
				}
			})
		},
		all : c,
		isRegistered : function(d) {
			return b[d] !== undefined
		},
		registerType : function(e, d) {
			b[e] = d;
			d.xtype = e
		},
		create : function(d, e) {
			return d.render ? d : new b[d.xtype || e](d)
		},
		registerPlugin : function(e, d) {
			a[e] = d;
			d.ptype = e
		},
		createPlugin : function(d, e) {
			return new a[d.ptype || e](d)
		}
	}
}();
Ext.reg = Ext.ComponentMgr.registerType;
Ext.preg = Ext.ComponentMgr.registerPlugin;
Ext.create = Ext.ComponentMgr.create;
Ext.Component = function(b) {
	b = b || {};
	if (b.initialConfig) {
		if (b.isAction) {
			this.baseAction = b
		}
		b = b.initialConfig
	} else {
		if (b.tagName || b.dom || Ext.isString(b)) {
			b = {
				applyTo : b,
				id : b.id || b
			}
		}
	}
	this.initialConfig = b;
	Ext.apply(this, b);
	this.addEvents("disable", "enable", "beforeshow", "show", "beforehide", "hide", "beforerender", "render",
			"afterrender", "beforedestroy", "destroy", "beforestaterestore", "staterestore", "beforestatesave",
			"statesave");
	this.getId();
	Ext.ComponentMgr.register(this);
	Ext.Component.superclass.constructor.call(this);
	if (this.baseAction) {
		this.baseAction.addComponent(this)
	}
	this.initComponent();
	if (this.plugins) {
		if (Ext.isArray(this.plugins)) {
			for ( var c = 0, a = this.plugins.length; c < a; c++) {
				this.plugins[c] = this.initPlugin(this.plugins[c])
			}
		} else {
			this.plugins = this.initPlugin(this.plugins)
		}
	}
	if (this.stateful !== false) {
		this.initState(b)
	}
	if (this.applyTo) {
		this.applyToMarkup(this.applyTo);
		delete this.applyTo
	} else {
		if (this.renderTo) {
			this.render(this.renderTo);
			delete this.renderTo
		}
	}
};
Ext.Component.AUTO_ID = 1000;
Ext
		.extend(
				Ext.Component,
				Ext.util.Observable,
				{
					disabled : false,
					hidden : false,
					autoEl : "div",
					disabledClass : "x-item-disabled",
					allowDomMove : true,
					autoShow : false,
					hideMode : "display",
					hideParent : false,
					rendered : false,
					ctype : "Ext.Component",
					actionMode : "el",
					getActionEl : function() {
						return this[this.actionMode]
					},
					initPlugin : function(a) {
						if (a.ptype && !Ext.isFunction(a.init)) {
							a = Ext.ComponentMgr.createPlugin(a)
						} else {
							if (Ext.isString(a)) {
								a = Ext.ComponentMgr.createPlugin({
									ptype : a
								})
							}
						}
						a.init(this);
						return a
					},
					initComponent : Ext.emptyFn,
					render : function(b, a) {
						if (!this.rendered && this.fireEvent("beforerender", this) !== false) {
							if (!b && this.el) {
								this.el = Ext.get(this.el);
								b = this.el.dom.parentNode;
								this.allowDomMove = false
							}
							this.container = Ext.get(b);
							if (this.ctCls) {
								this.container.addClass(this.ctCls)
							}
							this.rendered = true;
							if (a !== undefined) {
								if (Ext.isNumber(a)) {
									a = this.container.dom.childNodes[a]
								} else {
									a = Ext.getDom(a)
								}
							}
							this.onRender(this.container, a || null);
							if (this.autoShow) {
								this.el.removeClass([ "x-hidden", "x-hide-" + this.hideMode ])
							}
							if (this.cls) {
								this.el.addClass(this.cls);
								delete this.cls
							}
							if (this.style) {
								this.el.applyStyles(this.style);
								delete this.style
							}
							if (this.overCls) {
								this.el.addClassOnOver(this.overCls)
							}
							this.fireEvent("render", this);
							this.afterRender(this.container);
							if (this.hidden) {
								this.doHide()
							}
							if (this.disabled) {
								this.disable(true)
							}
							if (this.stateful !== false) {
								this.initStateEvents()
							}
							this.initRef();
							this.fireEvent("afterrender", this)
						}
						return this
					},
					initRef : function() {
						if (this.ref) {
							var d = this.ref.split("/");
							var c = d.length, b = 0;
							var a = this;
							while (b < c) {
								if (a.ownerCt) {
									a = a.ownerCt
								}
								b++
							}
							a[d[--b]] = this
						}
					},
					initState : function(a) {
						if (Ext.state.Manager) {
							var c = this.getStateId();
							if (c) {
								var b = Ext.state.Manager.get(c);
								if (b) {
									if (this.fireEvent("beforestaterestore", this, b) !== false) {
										this.applyState(b);
										this.fireEvent("staterestore", this, b)
									}
								}
							}
						}
					},
					getStateId : function() {
						return this.stateId
								|| ((this.id.indexOf("ext-comp-") == 0 || this.id.indexOf("ext-gen") == 0) ? null
										: this.id)
					},
					initStateEvents : function() {
						if (this.stateEvents) {
							for ( var a = 0, b; b = this.stateEvents[a]; a++) {
								this.on(b, this.saveState, this, {
									delay : 100
								})
							}
						}
					},
					applyState : function(b, a) {
						if (b) {
							Ext.apply(this, b)
						}
					},
					getState : function() {
						return null
					},
					saveState : function() {
						if (Ext.state.Manager && this.stateful !== false) {
							var b = this.getStateId();
							if (b) {
								var a = this.getState();
								if (this.fireEvent("beforestatesave", this, a) !== false) {
									Ext.state.Manager.set(b, a);
									this.fireEvent("statesave", this, a)
								}
							}
						}
					},
					applyToMarkup : function(a) {
						this.allowDomMove = false;
						this.el = Ext.get(a);
						this.render(this.el.dom.parentNode)
					},
					addClass : function(a) {
						if (this.el) {
							this.el.addClass(a)
						} else {
							this.cls = this.cls ? this.cls + " " + a : a
						}
						return this
					},
					removeClass : function(a) {
						if (this.el) {
							this.el.removeClass(a)
						} else {
							if (this.cls) {
								this.cls = this.cls.split(" ").remove(a).join(" ")
							}
						}
						return this
					},
					onRender : function(b, a) {
						if (!this.el && this.autoEl) {
							if (Ext.isString(this.autoEl)) {
								this.el = document.createElement(this.autoEl)
							} else {
								var c = document.createElement("div");
								Ext.DomHelper.overwrite(c, this.autoEl);
								this.el = c.firstChild
							}
							if (!this.el.id) {
								this.el.id = this.getId()
							}
						}
						if (this.el) {
							this.el = Ext.get(this.el);
							if (this.allowDomMove !== false) {
								b.dom.insertBefore(this.el.dom, a)
							}
						}
					},
					getAutoCreate : function() {
						var a = Ext.isObject(this.autoCreate) ? this.autoCreate : Ext.apply({}, this.defaultAutoCreate);
						if (this.id && !a.id) {
							a.id = this.id
						}
						return a
					},
					afterRender : Ext.emptyFn,
					destroy : function() {
						if (this.fireEvent("beforedestroy", this) !== false) {
							this.beforeDestroy();
							if (this.rendered) {
								this.el.removeAllListeners();
								this.el.remove();
								if (this.actionMode == "container" || this.removeMode == "container") {
									this.container.remove()
								}
							}
							this.onDestroy();
							Ext.ComponentMgr.unregister(this);
							this.fireEvent("destroy", this);
							this.purgeListeners()
						}
					},
					beforeDestroy : Ext.emptyFn,
					onDestroy : Ext.emptyFn,
					getEl : function() {
						return this.el
					},
					getId : function() {
						return this.id || (this.id = "ext-comp-" + (++Ext.Component.AUTO_ID))
					},
					getItemId : function() {
						return this.itemId || this.getId()
					},
					focus : function(b, a) {
						if (a) {
							this.focus.defer(Ext.isNumber(a) ? a : 10, this, [ b, false ]);
							return
						}
						if (this.rendered) {
							this.el.focus();
							if (b === true) {
								this.el.dom.select()
							}
						}
						return this
					},
					blur : function() {
						if (this.rendered) {
							this.el.blur()
						}
						return this
					},
					disable : function(a) {
						if (this.rendered) {
							this.onDisable()
						}
						this.disabled = true;
						if (a !== true) {
							this.fireEvent("disable", this)
						}
						return this
					},
					onDisable : function() {
						this.getActionEl().addClass(this.disabledClass);
						this.el.dom.disabled = true
					},
					enable : function() {
						if (this.rendered) {
							this.onEnable()
						}
						this.disabled = false;
						this.fireEvent("enable", this);
						return this
					},
					onEnable : function() {
						this.getActionEl().removeClass(this.disabledClass);
						this.el.dom.disabled = false
					},
					setDisabled : function(a) {
						return this[a ? "disable" : "enable"]()
					},
					show : function() {
						if (this.fireEvent("beforeshow", this) !== false) {
							this.hidden = false;
							if (this.autoRender) {
								this.render(Ext.isBoolean(this.autoRender) ? Ext.getBody() : this.autoRender)
							}
							if (this.rendered) {
								this.onShow()
							}
							this.fireEvent("show", this)
						}
						return this
					},
					onShow : function() {
						this.getVisibiltyEl().removeClass("x-hide-" + this.hideMode)
					},
					hide : function() {
						if (this.fireEvent("beforehide", this) !== false) {
							this.doHide();
							this.fireEvent("hide", this)
						}
						return this
					},
					doHide : function() {
						this.hidden = true;
						if (this.rendered) {
							this.onHide()
						}
					},
					onHide : function() {
						this.getVisibiltyEl().addClass("x-hide-" + this.hideMode)
					},
					getVisibiltyEl : function() {
						return this.hideParent ? this.container : this.getActionEl()
					},
					setVisible : function(a) {
						return this[a ? "show" : "hide"]()
					},
					isVisible : function() {
						return this.rendered && this.getVisibiltyEl().isVisible()
					},
					cloneConfig : function(b) {
						b = b || {};
						var c = b.id || Ext.id();
						var a = Ext.applyIf(b, this.initialConfig);
						a.id = c;
						return new this.constructor(a)
					},
					getXType : function() {
						return this.constructor.xtype
					},
					isXType : function(b, a) {
						if (Ext.isFunction(b)) {
							b = b.xtype
						} else {
							if (Ext.isObject(b)) {
								b = b.constructor.xtype
							}
						}
						return !a ? ("/" + this.getXTypes() + "/").indexOf("/" + b + "/") != -1
								: this.constructor.xtype == b
					},
					getXTypes : function() {
						var a = this.constructor;
						if (!a.xtypes) {
							var d = [], b = this;
							while (b && b.constructor.xtype) {
								d.unshift(b.constructor.xtype);
								b = b.constructor.superclass
							}
							a.xtypeChain = d;
							a.xtypes = d.join("/")
						}
						return a.xtypes
					},
					findParentBy : function(a) {
						for ( var b = this.ownerCt; (b != null) && !a(b, this); b = b.ownerCt) {
						}
						return b || null
					},
					findParentByType : function(a) {
						return Ext.isFunction(a) ? this.findParentBy(function(b) {
							return b.constructor === a
						}) : this.findParentBy(function(b) {
							return b.constructor.xtype === a
						})
					},
					getDomPositionEl : function() {
						return this.getPositionEl ? this.getPositionEl() : this.getEl()
					},
					purgeListeners : function() {
						Ext.Component.superclass.purgeListeners.call(this);
						if (this.mons) {
							this.on("beforedestroy", this.clearMons, this, {
								single : true
							})
						}
					},
					clearMons : function() {
						Ext.each(this.mons, function(a) {
							a.item.un(a.ename, a.fn, a.scope)
						}, this);
						this.mons = []
					},
					mon : function(f, b, d, c, a) {
						if (!this.mons) {
							this.mons = [];
							this.on("beforedestroy", this.clearMons, this, {
								single : true
							})
						}
						if (Ext.isObject(b)) {
							var i = /^(?:scope|delay|buffer|single|stopEvent|preventDefault|stopPropagation|normalized|args|delegate)$/;
							var h = b;
							for ( var g in h) {
								if (i.test(g)) {
									continue
								}
								if (Ext.isFunction(h[g])) {
									this.mons.push({
										item : f,
										ename : g,
										fn : h[g],
										scope : h.scope
									});
									f.on(g, h[g], h.scope, h)
								} else {
									this.mons.push({
										item : f,
										ename : g,
										fn : h[g],
										scope : h.scope
									});
									f.on(g, h[g])
								}
							}
							return
						}
						this.mons.push({
							item : f,
							ename : b,
							fn : d,
							scope : c
						});
						f.on(b, d, c, a)
					},
					mun : function(g, c, f, e) {
						var h, d;
						for ( var b = 0, a = this.mons.length; b < a; ++b) {
							d = this.mons[b];
							if (g === d.item && c == d.ename && f === d.fn && e === d.scope) {
								this.mons.splice(b, 1);
								g.un(c, f, e);
								h = true;
								break
							}
						}
						return h
					},
					nextSibling : function() {
						if (this.ownerCt) {
							var a = this.ownerCt.items.indexOf(this);
							if (a != -1 && a + 1 < this.ownerCt.items.getCount()) {
								return this.ownerCt.items.itemAt(a + 1)
							}
						}
						return null
					},
					previousSibling : function() {
						if (this.ownerCt) {
							var a = this.ownerCt.items.indexOf(this);
							if (a > 0) {
								return this.ownerCt.items.itemAt(a - 1)
							}
						}
						return null
					},
					getBubbleTarget : function() {
						return this.ownerCt
					}
				});
Ext.reg("component", Ext.Component);
Ext.Action = function(a) {
	this.initialConfig = a;
	this.itemId = a.itemId = (a.itemId || a.id || Ext.id());
	this.items = []
};
Ext.Action.prototype = {
	isAction : true,
	setText : function(a) {
		this.initialConfig.text = a;
		this.callEach("setText", [ a ])
	},
	getText : function() {
		return this.initialConfig.text
	},
	setIconClass : function(a) {
		this.initialConfig.iconCls = a;
		this.callEach("setIconClass", [ a ])
	},
	getIconClass : function() {
		return this.initialConfig.iconCls
	},
	setDisabled : function(a) {
		this.initialConfig.disabled = a;
		this.callEach("setDisabled", [ a ])
	},
	enable : function() {
		this.setDisabled(false)
	},
	disable : function() {
		this.setDisabled(true)
	},
	isDisabled : function() {
		return this.initialConfig.disabled
	},
	setHidden : function(a) {
		this.initialConfig.hidden = a;
		this.callEach("setVisible", [ !a ])
	},
	show : function() {
		this.setHidden(false)
	},
	hide : function() {
		this.setHidden(true)
	},
	isHidden : function() {
		return this.initialConfig.hidden
	},
	setHandler : function(b, a) {
		this.initialConfig.handler = b;
		this.initialConfig.scope = a;
		this.callEach("setHandler", [ b, a ])
	},
	each : function(b, a) {
		Ext.each(this.items, b, a)
	},
	callEach : function(e, b) {
		var d = this.items;
		for ( var c = 0, a = d.length; c < a; c++) {
			d[c][e].apply(d[c], b)
		}
	},
	addComponent : function(a) {
		this.items.push(a);
		a.on("destroy", this.removeComponent, this)
	},
	removeComponent : function(a) {
		this.items.remove(a)
	},
	execute : function() {
		this.initialConfig.handler.apply(this.initialConfig.scope || window, arguments)
	}
};
(function() {
	Ext.Layer = function(d, c) {
		d = d || {};
		var e = Ext.DomHelper;
		var g = d.parentEl, f = g ? Ext.getDom(g) : document.body;
		if (c) {
			this.dom = Ext.getDom(c)
		}
		if (!this.dom) {
			var h = d.dh || {
				tag : "div",
				cls : "x-layer"
			};
			this.dom = e.append(f, h)
		}
		if (d.cls) {
			this.addClass(d.cls)
		}
		this.constrain = d.constrain !== false;
		this.setVisibilityMode(Ext.Element.VISIBILITY);
		if (d.id) {
			this.id = this.dom.id = d.id
		} else {
			this.id = Ext.id(this.dom)
		}
		this.zindex = d.zindex || this.getZIndex();
		this.position("absolute", this.zindex);
		if (d.shadow) {
			this.shadowOffset = d.shadowOffset || 4;
			this.shadow = new Ext.Shadow({
				offset : this.shadowOffset,
				mode : d.shadow
			})
		} else {
			this.shadowOffset = 0
		}
		this.useShim = d.shim !== false && Ext.useShims;
		this.useDisplay = d.useDisplay;
		this.hide()
	};
	var a = Ext.Element.prototype;
	var b = [];
	Ext.extend(Ext.Layer, Ext.Element, {
		getZIndex : function() {
			return this.zindex || parseInt((this.getShim() || this).getStyle("z-index"), 10) || 11000
		},
		getShim : function() {
			if (!this.useShim) {
				return null
			}
			if (this.shim) {
				return this.shim
			}
			var d = b.shift();
			if (!d) {
				d = this.createShim();
				d.enableDisplayMode("block");
				d.dom.style.display = "none";
				d.dom.style.visibility = "visible"
			}
			var c = this.dom.parentNode;
			if (d.dom.parentNode != c) {
				c.insertBefore(d.dom, this.dom)
			}
			d.setStyle("z-index", this.getZIndex() - 2);
			this.shim = d;
			return d
		},
		hideShim : function() {
			if (this.shim) {
				this.shim.setDisplayed(false);
				b.push(this.shim);
				delete this.shim
			}
		},
		disableShadow : function() {
			if (this.shadow) {
				this.shadowDisabled = true;
				this.shadow.hide();
				this.lastShadowOffset = this.shadowOffset;
				this.shadowOffset = 0
			}
		},
		enableShadow : function(c) {
			if (this.shadow) {
				this.shadowDisabled = false;
				this.shadowOffset = this.lastShadowOffset;
				delete this.lastShadowOffset;
				if (c) {
					this.sync(true)
				}
			}
		},
		sync : function(c) {
			var j = this.shadow;
			if (!this.updating && this.isVisible() && (j || this.useShim)) {
				var f = this.getShim();
				var i = this.getWidth(), e = this.getHeight();
				var d = this.getLeft(true), k = this.getTop(true);
				if (j && !this.shadowDisabled) {
					if (c && !j.isVisible()) {
						j.show(this)
					} else {
						j.realign(d, k, i, e)
					}
					if (f) {
						if (c) {
							f.show()
						}
						var g = j.adjusts, m = f.dom.style;
						m.left = (Math.min(d, d + g.l)) + "px";
						m.top = (Math.min(k, k + g.t)) + "px";
						m.width = (i + g.w) + "px";
						m.height = (e + g.h) + "px"
					}
				} else {
					if (f) {
						if (c) {
							f.show()
						}
						f.setSize(i, e);
						f.setLeftTop(d, k)
					}
				}
			}
		},
		destroy : function() {
			this.hideShim();
			if (this.shadow) {
				this.shadow.hide()
			}
			this.removeAllListeners();
			Ext.removeNode(this.dom);
			Ext.Element.uncache(this.id)
		},
		remove : function() {
			this.destroy()
		},
		beginUpdate : function() {
			this.updating = true
		},
		endUpdate : function() {
			this.updating = false;
			this.sync(true)
		},
		hideUnders : function(c) {
			if (this.shadow) {
				this.shadow.hide()
			}
			this.hideShim()
		},
		constrainXY : function() {
			if (this.constrain) {
				var i = Ext.lib.Dom.getViewWidth(), d = Ext.lib.Dom.getViewHeight();
				var n = Ext.getDoc().getScroll();
				var m = this.getXY();
				var j = m[0], g = m[1];
				var c = this.shadowOffset;
				var k = this.dom.offsetWidth + c, e = this.dom.offsetHeight + c;
				var f = false;
				if ((j + k) > i + n.left) {
					j = i - k - c;
					f = true
				}
				if ((g + e) > d + n.top) {
					g = d - e - c;
					f = true
				}
				if (j < n.left) {
					j = n.left;
					f = true
				}
				if (g < n.top) {
					g = n.top;
					f = true
				}
				if (f) {
					if (this.avoidY) {
						var l = this.avoidY;
						if (g <= l && (g + e) >= l) {
							g = l - e - 5
						}
					}
					m = [ j, g ];
					this.storeXY(m);
					a.setXY.call(this, m);
					this.sync()
				}
			}
			return this
		},
		isVisible : function() {
			return this.visible
		},
		showAction : function() {
			this.visible = true;
			if (this.useDisplay === true) {
				this.setDisplayed("")
			} else {
				if (this.lastXY) {
					a.setXY.call(this, this.lastXY)
				} else {
					if (this.lastLT) {
						a.setLeftTop.call(this, this.lastLT[0], this.lastLT[1])
					}
				}
			}
		},
		hideAction : function() {
			this.visible = false;
			if (this.useDisplay === true) {
				this.setDisplayed(false)
			} else {
				this.setLeftTop(-10000, -10000)
			}
		},
		setVisible : function(h, g, j, k, i) {
			if (h) {
				this.showAction()
			}
			if (g && h) {
				var f = function() {
					this.sync(true);
					if (k) {
						k()
					}
				}.createDelegate(this);
				a.setVisible.call(this, true, true, j, f, i)
			} else {
				if (!h) {
					this.hideUnders(true)
				}
				var f = k;
				if (g) {
					f = function() {
						this.hideAction();
						if (k) {
							k()
						}
					}.createDelegate(this)
				}
				a.setVisible.call(this, h, g, j, f, i);
				if (h) {
					this.sync(true)
				} else {
					if (!g) {
						this.hideAction()
					}
				}
			}
			return this
		},
		storeXY : function(c) {
			delete this.lastLT;
			this.lastXY = c
		},
		storeLeftTop : function(d, c) {
			delete this.lastXY;
			this.lastLT = [ d, c ]
		},
		beforeFx : function() {
			this.beforeAction();
			return Ext.Layer.superclass.beforeFx.apply(this, arguments)
		},
		afterFx : function() {
			Ext.Layer.superclass.afterFx.apply(this, arguments);
			this.sync(this.isVisible())
		},
		beforeAction : function() {
			if (!this.updating && this.shadow) {
				this.shadow.hide()
			}
		},
		setLeft : function(c) {
			this.storeLeftTop(c, this.getTop(true));
			a.setLeft.apply(this, arguments);
			this.sync();
			return this
		},
		setTop : function(c) {
			this.storeLeftTop(this.getLeft(true), c);
			a.setTop.apply(this, arguments);
			this.sync();
			return this
		},
		setLeftTop : function(d, c) {
			this.storeLeftTop(d, c);
			a.setLeftTop.apply(this, arguments);
			this.sync();
			return this
		},
		setXY : function(i, g, j, k, h) {
			this.fixDisplay();
			this.beforeAction();
			this.storeXY(i);
			var f = this.createCB(k);
			a.setXY.call(this, i, g, j, f, h);
			if (!g) {
				f()
			}
			return this
		},
		createCB : function(e) {
			var d = this;
			return function() {
				d.constrainXY();
				d.sync(true);
				if (e) {
					e()
				}
			}
		},
		setX : function(f, g, i, j, h) {
			this.setXY([ f, this.getY() ], g, i, j, h);
			return this
		},
		setY : function(j, f, h, i, g) {
			this.setXY([ this.getX(), j ], f, h, i, g);
			return this
		},
		setSize : function(i, j, g, l, m, k) {
			this.beforeAction();
			var f = this.createCB(m);
			a.setSize.call(this, i, j, g, l, f, k);
			if (!g) {
				f()
			}
			return this
		},
		setWidth : function(h, g, j, k, i) {
			this.beforeAction();
			var f = this.createCB(k);
			a.setWidth.call(this, h, g, j, f, i);
			if (!g) {
				f()
			}
			return this
		},
		setHeight : function(i, g, k, l, j) {
			this.beforeAction();
			var f = this.createCB(l);
			a.setHeight.call(this, i, g, k, f, j);
			if (!g) {
				f()
			}
			return this
		},
		setBounds : function(n, l, o, g, m, j, k, i) {
			this.beforeAction();
			var f = this.createCB(k);
			if (!m) {
				this.storeXY([ n, l ]);
				a.setXY.call(this, [ n, l ]);
				a.setSize.call(this, o, g, m, j, f, i);
				f()
			} else {
				a.setBounds.call(this, n, l, o, g, m, j, f, i)
			}
			return this
		},
		setZIndex : function(c) {
			this.zindex = c;
			this.setStyle("z-index", c + 2);
			if (this.shadow) {
				this.shadow.setZIndex(c + 1)
			}
			if (this.shim) {
				this.shim.setStyle("z-index", c)
			}
			return this
		}
	})
})();
Ext.Shadow = function(d) {
	Ext.apply(this, d);
	if (typeof this.mode != "string") {
		this.mode = this.defaultMode
	}
	var e = this.offset, c = {
		h : 0
	};
	var b = Math.floor(this.offset / 2);
	switch (this.mode.toLowerCase()) {
	case "drop":
		c.w = 0;
		c.l = c.t = e;
		c.t -= 1;
		if (Ext.isIE) {
			c.l -= this.offset + b;
			c.t -= this.offset + b;
			c.w -= b;
			c.h -= b;
			c.t += 1
		}
		break;
	case "sides":
		c.w = (e * 2);
		c.l = -e;
		c.t = e - 1;
		if (Ext.isIE) {
			c.l -= (this.offset - b);
			c.t -= this.offset + b;
			c.l += 1;
			c.w -= (this.offset - b) * 2;
			c.w -= b + 1;
			c.h -= 1
		}
		break;
	case "frame":
		c.w = c.h = (e * 2);
		c.l = c.t = -e;
		c.t += 1;
		c.h -= 2;
		if (Ext.isIE) {
			c.l -= (this.offset - b);
			c.t -= (this.offset - b);
			c.l += 1;
			c.w -= (this.offset + b + 1);
			c.h -= (this.offset + b);
			c.h += 1
		}
		break
	}
	this.adjusts = c
};
Ext.Shadow.prototype = {
	offset : 4,
	defaultMode : "drop",
	show : function(a) {
		a = Ext.get(a);
		if (!this.el) {
			this.el = Ext.Shadow.Pool.pull();
			if (this.el.dom.nextSibling != a.dom) {
				this.el.insertBefore(a)
			}
		}
		this.el.setStyle("z-index", this.zIndex || parseInt(a.getStyle("z-index"), 10) - 1);
		if (Ext.isIE) {
			this.el.dom.style.filter = "progid:DXImageTransform.Microsoft.alpha(opacity=50) progid:DXImageTransform.Microsoft.Blur(pixelradius="
					+ (this.offset) + ")"
		}
		this.realign(a.getLeft(true), a.getTop(true), a.getWidth(), a.getHeight());
		this.el.dom.style.display = "block"
	},
	isVisible : function() {
		return this.el ? true : false
	},
	realign : function(b, q, p, f) {
		if (!this.el) {
			return
		}
		var m = this.adjusts, j = this.el.dom, r = j.style;
		var g = 0;
		r.left = (b + m.l) + "px";
		r.top = (q + m.t) + "px";
		var o = (p + m.w), e = (f + m.h), i = o + "px", n = e + "px";
		if (r.width != i || r.height != n) {
			r.width = i;
			r.height = n;
			if (!Ext.isIE) {
				var k = j.childNodes;
				var c = Math.max(0, (o - 12)) + "px";
				k[0].childNodes[1].style.width = c;
				k[1].childNodes[1].style.width = c;
				k[2].childNodes[1].style.width = c;
				k[1].style.height = Math.max(0, (e - 12)) + "px"
			}
		}
	},
	hide : function() {
		if (this.el) {
			this.el.dom.style.display = "none";
			Ext.Shadow.Pool.push(this.el);
			delete this.el
		}
	},
	setZIndex : function(a) {
		this.zIndex = a;
		if (this.el) {
			this.el.setStyle("z-index", a)
		}
	}
};
Ext.Shadow.Pool = function() {
	var b = [];
	var a = Ext.isIE ? '<div class="x-ie-shadow"></div>'
			: '<div class="x-shadow"><div class="xst"><div class="xstl"></div><div class="xstc"></div><div class="xstr"></div></div><div class="xsc"><div class="xsml"></div><div class="xsmc"></div><div class="xsmr"></div></div><div class="xsb"><div class="xsbl"></div><div class="xsbc"></div><div class="xsbr"></div></div></div>';
	return {
		pull : function() {
			var c = b.shift();
			if (!c) {
				c = Ext.get(Ext.DomHelper.insertHtml("beforeBegin", document.body.firstChild, a));
				c.autoBoxAdjust = false
			}
			return c
		},
		push : function(c) {
			b.push(c)
		}
	}
}();
Ext.BoxComponent = Ext.extend(Ext.Component, {
	initComponent : function() {
		Ext.BoxComponent.superclass.initComponent.call(this);
		this.addEvents("resize", "move")
	},
	boxReady : false,
	deferHeight : false,
	setSize : function(b, d) {
		if (typeof b == "object") {
			d = b.height;
			b = b.width
		}
		if (!this.boxReady) {
			this.width = b;
			this.height = d;
			return this
		}
		if (this.cacheSizes !== false && this.lastSize && this.lastSize.width == b && this.lastSize.height == d) {
			return this
		}
		this.lastSize = {
			width : b,
			height : d
		};
		var c = this.adjustSize(b, d);
		var f = c.width, a = c.height;
		if (f !== undefined || a !== undefined) {
			var e = this.getResizeEl();
			if (!this.deferHeight && f !== undefined && a !== undefined) {
				e.setSize(f, a)
			} else {
				if (!this.deferHeight && a !== undefined) {
					e.setHeight(a)
				} else {
					if (f !== undefined) {
						e.setWidth(f)
					}
				}
			}
			this.onResize(f, a, b, d);
			this.fireEvent("resize", this, f, a, b, d)
		}
		return this
	},
	setWidth : function(a) {
		return this.setSize(a)
	},
	setHeight : function(a) {
		return this.setSize(undefined, a)
	},
	getSize : function() {
		return this.getResizeEl().getSize()
	},
	getWidth : function() {
		return this.getResizeEl().getWidth()
	},
	getHeight : function() {
		return this.getResizeEl().getHeight()
	},
	getOuterSize : function() {
		var a = this.getResizeEl();
		return {
			width : a.getWidth() + a.getMargins("lr"),
			height : a.getHeight() + a.getMargins("tb")
		}
	},
	getPosition : function(a) {
		var b = this.getPositionEl();
		if (a === true) {
			return [ b.getLeft(true), b.getTop(true) ]
		}
		return this.xy || b.getXY()
	},
	getBox : function(a) {
		var c = this.getPosition(a);
		var b = this.getSize();
		b.x = c[0];
		b.y = c[1];
		return b
	},
	updateBox : function(a) {
		this.setSize(a.width, a.height);
		this.setPagePosition(a.x, a.y);
		return this
	},
	getResizeEl : function() {
		return this.resizeEl || this.el
	},
	getPositionEl : function() {
		return this.positionEl || this.el
	},
	setPosition : function(a, f) {
		if (a && typeof a[1] == "number") {
			f = a[1];
			a = a[0]
		}
		this.x = a;
		this.y = f;
		if (!this.boxReady) {
			return this
		}
		var b = this.adjustPosition(a, f);
		var e = b.x, d = b.y;
		var c = this.getPositionEl();
		if (e !== undefined || d !== undefined) {
			if (e !== undefined && d !== undefined) {
				c.setLeftTop(e, d)
			} else {
				if (e !== undefined) {
					c.setLeft(e)
				} else {
					if (d !== undefined) {
						c.setTop(d)
					}
				}
			}
			this.onPosition(e, d);
			this.fireEvent("move", this, e, d)
		}
		return this
	},
	setPagePosition : function(a, c) {
		if (a && typeof a[1] == "number") {
			c = a[1];
			a = a[0]
		}
		this.pageX = a;
		this.pageY = c;
		if (!this.boxReady) {
			return
		}
		if (a === undefined || c === undefined) {
			return
		}
		var b = this.getPositionEl().translatePoints(a, c);
		this.setPosition(b.left, b.top);
		return this
	},
	onRender : function(b, a) {
		Ext.BoxComponent.superclass.onRender.call(this, b, a);
		if (this.resizeEl) {
			this.resizeEl = Ext.get(this.resizeEl)
		}
		if (this.positionEl) {
			this.positionEl = Ext.get(this.positionEl)
		}
	},
	afterRender : function() {
		Ext.BoxComponent.superclass.afterRender.call(this);
		this.boxReady = true;
		this.setSize(this.width, this.height);
		if (this.x || this.y) {
			this.setPosition(this.x, this.y)
		} else {
			if (this.pageX || this.pageY) {
				this.setPagePosition(this.pageX, this.pageY)
			}
		}
	},
	syncSize : function() {
		delete this.lastSize;
		this.setSize(this.autoWidth ? undefined : this.getResizeEl().getWidth(), this.autoHeight ? undefined : this
				.getResizeEl().getHeight());
		return this
	},
	onResize : function(d, b, a, c) {
	},
	onPosition : function(a, b) {
	},
	adjustSize : function(a, b) {
		if (this.autoWidth) {
			a = "auto"
		}
		if (this.autoHeight) {
			b = "auto"
		}
		return {
			width : a,
			height : b
		}
	},
	adjustPosition : function(a, b) {
		return {
			x : a,
			y : b
		}
	}
});
Ext.reg("box", Ext.BoxComponent);
Ext.Spacer = Ext.extend(Ext.BoxComponent, {
	autoEl : "div"
});
Ext.reg("spacer", Ext.Spacer);
Ext.SplitBar = function(c, e, b, d, a) {
	this.el = Ext.get(c, true);
	this.el.dom.unselectable = "on";
	this.resizingEl = Ext.get(e, true);
	this.orientation = b || Ext.SplitBar.HORIZONTAL;
	this.minSize = 0;
	this.maxSize = 2000;
	this.animate = false;
	this.useShim = false;
	this.shim = null;
	if (!a) {
		this.proxy = Ext.SplitBar.createProxy(this.orientation)
	} else {
		this.proxy = Ext.get(a).dom
	}
	this.dd = new Ext.dd.DDProxy(this.el.dom.id, "XSplitBars", {
		dragElId : this.proxy.id
	});
	this.dd.b4StartDrag = this.onStartProxyDrag.createDelegate(this);
	this.dd.endDrag = this.onEndProxyDrag.createDelegate(this);
	this.dragSpecs = {};
	this.adapter = new Ext.SplitBar.BasicLayoutAdapter();
	this.adapter.init(this);
	if (this.orientation == Ext.SplitBar.HORIZONTAL) {
		this.placement = d || (this.el.getX() > this.resizingEl.getX() ? Ext.SplitBar.LEFT : Ext.SplitBar.RIGHT);
		this.el.addClass("x-splitbar-h")
	} else {
		this.placement = d || (this.el.getY() > this.resizingEl.getY() ? Ext.SplitBar.TOP : Ext.SplitBar.BOTTOM);
		this.el.addClass("x-splitbar-v")
	}
	this.addEvents("resize", "moved", "beforeresize", "beforeapply");
	Ext.SplitBar.superclass.constructor.call(this)
};
Ext.extend(Ext.SplitBar, Ext.util.Observable, {
	onStartProxyDrag : function(a, e) {
		this.fireEvent("beforeresize", this);
		this.overlay = Ext.DomHelper.append(document.body, {
			cls : "x-drag-overlay",
			html : "&#160;"
		}, true);
		this.overlay.unselectable();
		this.overlay.setSize(Ext.lib.Dom.getViewWidth(true), Ext.lib.Dom.getViewHeight(true));
		this.overlay.show();
		Ext.get(this.proxy).setDisplayed("block");
		var c = this.adapter.getElementSize(this);
		this.activeMinSize = this.getMinimumSize();
		this.activeMaxSize = this.getMaximumSize();
		var d = c - this.activeMinSize;
		var b = Math.max(this.activeMaxSize - c, 0);
		if (this.orientation == Ext.SplitBar.HORIZONTAL) {
			this.dd.resetConstraints();
			this.dd.setXConstraint(this.placement == Ext.SplitBar.LEFT ? d : b, this.placement == Ext.SplitBar.LEFT ? b
					: d, this.tickSize);
			this.dd.setYConstraint(0, 0)
		} else {
			this.dd.resetConstraints();
			this.dd.setXConstraint(0, 0);
			this.dd.setYConstraint(this.placement == Ext.SplitBar.TOP ? d : b, this.placement == Ext.SplitBar.TOP ? b
					: d, this.tickSize)
		}
		this.dragSpecs.startSize = c;
		this.dragSpecs.startPoint = [ a, e ];
		Ext.dd.DDProxy.prototype.b4StartDrag.call(this.dd, a, e)
	},
	onEndProxyDrag : function(c) {
		Ext.get(this.proxy).setDisplayed(false);
		var b = Ext.lib.Event.getXY(c);
		if (this.overlay) {
			Ext.destroy(this.overlay);
			delete this.overlay
		}
		var a;
		if (this.orientation == Ext.SplitBar.HORIZONTAL) {
			a = this.dragSpecs.startSize
					+ (this.placement == Ext.SplitBar.LEFT ? b[0] - this.dragSpecs.startPoint[0]
							: this.dragSpecs.startPoint[0] - b[0])
		} else {
			a = this.dragSpecs.startSize
					+ (this.placement == Ext.SplitBar.TOP ? b[1] - this.dragSpecs.startPoint[1]
							: this.dragSpecs.startPoint[1] - b[1])
		}
		a = Math.min(Math.max(a, this.activeMinSize), this.activeMaxSize);
		if (a != this.dragSpecs.startSize) {
			if (this.fireEvent("beforeapply", this, a) !== false) {
				this.adapter.setElementSize(this, a);
				this.fireEvent("moved", this, a);
				this.fireEvent("resize", this, a)
			}
		}
	},
	getAdapter : function() {
		return this.adapter
	},
	setAdapter : function(a) {
		this.adapter = a;
		this.adapter.init(this)
	},
	getMinimumSize : function() {
		return this.minSize
	},
	setMinimumSize : function(a) {
		this.minSize = a
	},
	getMaximumSize : function() {
		return this.maxSize
	},
	setMaximumSize : function(a) {
		this.maxSize = a
	},
	setCurrentSize : function(b) {
		var a = this.animate;
		this.animate = false;
		this.adapter.setElementSize(this, b);
		this.animate = a
	},
	destroy : function(a) {
		Ext.destroy(this.shim, Ext.get(this.proxy));
		this.dd.unreg();
		if (a) {
			this.el.remove()
		}
		this.purgeListeners()
	}
});
Ext.SplitBar.createProxy = function(b) {
	var c = new Ext.Element(document.createElement("div"));
	c.unselectable();
	var a = "x-splitbar-proxy";
	c.addClass(a + " " + (b == Ext.SplitBar.HORIZONTAL ? a + "-h" : a + "-v"));
	document.body.appendChild(c.dom);
	return c.dom
};
Ext.SplitBar.BasicLayoutAdapter = function() {
};
Ext.SplitBar.BasicLayoutAdapter.prototype = {
	init : function(a) {
	},
	getElementSize : function(a) {
		if (a.orientation == Ext.SplitBar.HORIZONTAL) {
			return a.resizingEl.getWidth()
		} else {
			return a.resizingEl.getHeight()
		}
	},
	setElementSize : function(b, a, c) {
		if (b.orientation == Ext.SplitBar.HORIZONTAL) {
			if (!b.animate) {
				b.resizingEl.setWidth(a);
				if (c) {
					c(b, a)
				}
			} else {
				b.resizingEl.setWidth(a, true, 0.1, c, "easeOut")
			}
		} else {
			if (!b.animate) {
				b.resizingEl.setHeight(a);
				if (c) {
					c(b, a)
				}
			} else {
				b.resizingEl.setHeight(a, true, 0.1, c, "easeOut")
			}
		}
	}
};
Ext.SplitBar.AbsoluteLayoutAdapter = function(a) {
	this.basic = new Ext.SplitBar.BasicLayoutAdapter();
	this.container = Ext.get(a)
};
Ext.SplitBar.AbsoluteLayoutAdapter.prototype = {
	init : function(a) {
		this.basic.init(a)
	},
	getElementSize : function(a) {
		return this.basic.getElementSize(a)
	},
	setElementSize : function(b, a, c) {
		this.basic.setElementSize(b, a, this.moveSplitter.createDelegate(this, [ b ]))
	},
	moveSplitter : function(a) {
		var b = Ext.SplitBar;
		switch (a.placement) {
		case b.LEFT:
			a.el.setX(a.resizingEl.getRight());
			break;
		case b.RIGHT:
			a.el.setStyle("right", (this.container.getWidth() - a.resizingEl.getLeft()) + "px");
			break;
		case b.TOP:
			a.el.setY(a.resizingEl.getBottom());
			break;
		case b.BOTTOM:
			a.el.setY(a.resizingEl.getTop() - a.el.getHeight());
			break
		}
	}
};
Ext.SplitBar.VERTICAL = 1;
Ext.SplitBar.HORIZONTAL = 2;
Ext.SplitBar.LEFT = 1;
Ext.SplitBar.RIGHT = 2;
Ext.SplitBar.TOP = 3;
Ext.SplitBar.BOTTOM = 4;
Ext.Container = Ext.extend(Ext.BoxComponent, {
	bufferResize : 100,
	autoDestroy : true,
	forceLayout : false,
	defaultType : "panel",
	initComponent : function() {
		Ext.Container.superclass.initComponent.call(this);
		this.addEvents("afterlayout", "beforeadd", "beforeremove", "add", "remove");
		this.enableBubble("add", "remove");
		var a = this.items;
		if (a) {
			delete this.items;
			if (Ext.isArray(a) && a.length > 0) {
				this.add.apply(this, a)
			} else {
				this.add(a)
			}
		}
	},
	initItems : function() {
		if (!this.items) {
			this.items = new Ext.util.MixedCollection(false, this.getComponentId);
			this.getLayout()
		}
	},
	setLayout : function(a) {
		if (this.layout && this.layout != a) {
			this.layout.setContainer(null)
		}
		this.initItems();
		this.layout = a;
		a.setContainer(this)
	},
	render : function() {
		Ext.Container.superclass.render.apply(this, arguments);
		if (this.layout) {
			if (Ext.isObject(this.layout) && !this.layout.layout) {
				this.layoutConfig = this.layout;
				this.layout = this.layoutConfig.type
			}
			if (typeof this.layout == "string") {
				this.layout = new Ext.Container.LAYOUTS[this.layout.toLowerCase()](this.layoutConfig)
			}
			this.setLayout(this.layout);
			if (this.activeItem !== undefined) {
				var a = this.activeItem;
				delete this.activeItem;
				this.layout.setActiveItem(a)
			}
		}
		if (!this.ownerCt) {
			this.doLayout(false, true)
		}
		if (this.monitorResize === true) {
			Ext.EventManager.onWindowResize(this.doLayout, this, [ false ])
		}
	},
	getLayoutTarget : function() {
		return this.el
	},
	getComponentId : function(a) {
		return a.getItemId()
	},
	add : function(a) {
		this.initItems();
		var b = arguments.length > 1;
		if (b || Ext.isArray(a)) {
			Ext.each(b ? arguments : a, function(f) {
				this.add(f)
			}, this);
			return
		}
		var e = this.lookupComponent(this.applyDefaults(a));
		var d = this.items.length;
		if (this.fireEvent("beforeadd", this, e, d) !== false && this.onBeforeAdd(e) !== false) {
			this.items.add(e);
			e.ownerCt = this;
			this.fireEvent("add", this, e, d)
		}
		return e
	},
	insert : function(f, e) {
		this.initItems();
		var d = arguments, b = d.length;
		if (b > 2) {
			for ( var g = b - 1; g >= 1; --g) {
				this.insert(f, d[g])
			}
			return
		}
		var h = this.lookupComponent(this.applyDefaults(e));
		if (h.ownerCt == this && this.items.indexOf(h) < f) {
			--f
		}
		if (this.fireEvent("beforeadd", this, h, f) !== false && this.onBeforeAdd(h) !== false) {
			this.items.insert(f, h);
			h.ownerCt = this;
			this.fireEvent("add", this, h, f)
		}
		return h
	},
	applyDefaults : function(a) {
		if (this.defaults) {
			if (typeof a == "string") {
				a = Ext.ComponentMgr.get(a);
				Ext.apply(a, this.defaults)
			} else {
				if (!a.events) {
					Ext.applyIf(a, this.defaults)
				} else {
					Ext.apply(a, this.defaults)
				}
			}
		}
		return a
	},
	onBeforeAdd : function(a) {
		if (a.ownerCt) {
			a.ownerCt.remove(a, false)
		}
		if (this.hideBorders === true) {
			a.border = (a.border === true)
		}
	},
	remove : function(a, b) {
		this.initItems();
		var d = this.getComponent(a);
		if (d && this.fireEvent("beforeremove", this, d) !== false) {
			this.items.remove(d);
			delete d.ownerCt;
			if (b === true || (b !== false && this.autoDestroy)) {
				d.destroy()
			}
			if (this.layout && this.layout.activeItem == d) {
				delete this.layout.activeItem
			}
			this.fireEvent("remove", this, d)
		}
		return d
	},
	removeAll : function(c) {
		this.initItems();
		var e, f = [], b = [];
		this.items.each(function(g) {
			f.push(g)
		});
		for ( var d = 0, a = f.length; d < a; ++d) {
			e = f[d];
			this.remove(e, c);
			if (e.ownerCt !== this) {
				b.push(e)
			}
		}
		return b
	},
	getComponent : function(a) {
		if (Ext.isObject(a)) {
			return a
		}
		return this.items.get(a)
	},
	lookupComponent : function(a) {
		if (typeof a == "string") {
			return Ext.ComponentMgr.get(a)
		} else {
			if (!a.events) {
				return this.createComponent(a)
			}
		}
		return a
	},
	createComponent : function(a) {
		return Ext.create(a, this.defaultType)
	},
	doLayout : function(f, e) {
		var j = this.rendered, h = this.forceLayout;
		if (!this.isVisible() || this.collapsed) {
			this.deferLayout = this.deferLayout || !f;
			if (!(e || h)) {
				return
			}
			f = f && !this.deferLayout
		} else {
			delete this.deferLayout
		}
		if (j && this.layout) {
			this.layout.layout()
		}
		if (f !== true && this.items) {
			var d = this.items.items;
			for ( var b = 0, a = d.length; b < a; b++) {
				var g = d[b];
				if (g.doLayout) {
					g.forceLayout = h;
					g.doLayout()
				}
			}
		}
		if (j) {
			this.onLayout(f, e)
		}
		delete this.forceLayout
	},
	onLayout : Ext.emptyFn,
	onShow : function() {
		Ext.Container.superclass.onShow.call(this);
		if (this.deferLayout !== undefined) {
			this.doLayout(true)
		}
	},
	getLayout : function() {
		if (!this.layout) {
			var a = new Ext.layout.ContainerLayout(this.layoutConfig);
			this.setLayout(a)
		}
		return this.layout
	},
	beforeDestroy : function() {
		if (this.items) {
			Ext.destroy.apply(Ext, this.items.items)
		}
		if (this.monitorResize) {
			Ext.EventManager.removeResizeListener(this.doLayout, this)
		}
		Ext.destroy(this.layout);
		Ext.Container.superclass.beforeDestroy.call(this)
	},
	bubble : function(c, b, a) {
		var d = this;
		while (d) {
			if (c.apply(b || d, a || [ d ]) === false) {
				break
			}
			d = d.ownerCt
		}
		return this
	},
	cascade : function(f, e, b) {
		if (f.apply(e || this, b || [ this ]) !== false) {
			if (this.items) {
				var d = this.items.items;
				for ( var c = 0, a = d.length; c < a; c++) {
					if (d[c].cascade) {
						d[c].cascade(f, e, b)
					} else {
						f.apply(e || d[c], b || [ d[c] ])
					}
				}
			}
		}
		return this
	},
	findById : function(c) {
		var a, b = this;
		this.cascade(function(d) {
			if (b != d && d.id === c) {
				a = d;
				return false
			}
		});
		return a || null
	},
	findByType : function(b, a) {
		return this.findBy(function(d) {
			return d.isXType(b, a)
		})
	},
	find : function(b, a) {
		return this.findBy(function(d) {
			return d[b] === a
		})
	},
	findBy : function(d, c) {
		var a = [], b = this;
		this.cascade(function(e) {
			if (b != e && d.call(c || e, e, b) === true) {
				a.push(e)
			}
		});
		return a
	},
	get : function(a) {
		return this.items.get(a)
	}
});
Ext.Container.LAYOUTS = {};
Ext.reg("container", Ext.Container);
Ext.layout.ContainerLayout = function(a) {
	Ext.apply(this, a)
};
Ext.layout.ContainerLayout.prototype = {
	monitorResize : false,
	activeItem : null,
	layout : function() {
		var a = this.container.getLayoutTarget();
		this.onLayout(this.container, a);
		this.container.fireEvent("afterlayout", this.container, this)
	},
	onLayout : function(a, b) {
		this.renderAll(a, b)
	},
	isValidParent : function(b, a) {
		return a && b.getDomPositionEl().dom.parentNode == (a.dom || a)
	},
	renderAll : function(e, f) {
		var b = e.items.items;
		for ( var d = 0, a = b.length; d < a; d++) {
			var g = b[d];
			if (g && (!g.rendered || !this.isValidParent(g, f))) {
				this.renderItem(g, d, f)
			}
		}
	},
	renderItem : function(d, a, b) {
		if (d && !d.rendered) {
			d.render(b, a);
			this.configureItem(d, a)
		} else {
			if (d && !this.isValidParent(d, b)) {
				if (typeof a == "number") {
					a = b.dom.childNodes[a]
				}
				b.dom.insertBefore(d.getDomPositionEl().dom, a || null);
				d.container = b;
				this.configureItem(d, a)
			}
		}
	},
	configureItem : function(d, a) {
		if (this.extraCls) {
			var b = d.getPositionEl ? d.getPositionEl() : d;
			b.addClass(this.extraCls)
		}
		if (this.renderHidden && d != this.activeItem) {
			d.hide()
		}
		if (d.doLayout) {
			d.doLayout(false, this.forceLayout)
		}
	},
	onResize : function() {
		if (this.container.collapsed) {
			return
		}
		var a = this.container.bufferResize;
		if (a) {
			if (!this.resizeTask) {
				this.resizeTask = new Ext.util.DelayedTask(this.runLayout, this);
				this.resizeBuffer = typeof a == "number" ? a : 100
			}
			this.resizeTask.delay(this.resizeBuffer)
		} else {
			this.runLayout()
		}
	},
	runLayout : function() {
		this.layout();
		this.container.onLayout()
	},
	setContainer : function(a) {
		if (this.monitorResize && a != this.container) {
			if (this.container) {
				this.container.un("resize", this.onResize, this);
				this.container.un("bodyresize", this.onResize, this)
			}
			if (a) {
				a.on({
					scope : this,
					resize : this.onResize,
					bodyresize : this.onResize
				})
			}
		}
		this.container = a
	},
	parseMargins : function(b) {
		if (typeof b == "number") {
			b = b.toString()
		}
		var c = b.split(" ");
		var a = c.length;
		if (a == 1) {
			c[1] = c[0];
			c[2] = c[0];
			c[3] = c[0]
		}
		if (a == 2) {
			c[2] = c[0];
			c[3] = c[1]
		}
		if (a == 3) {
			c[3] = c[1]
		}
		return {
			top : parseInt(c[0], 10) || 0,
			right : parseInt(c[1], 10) || 0,
			bottom : parseInt(c[2], 10) || 0,
			left : parseInt(c[3], 10) || 0
		}
	},
	fieldTpl : (function() {
		var a = new Ext.Template('<div class="x-form-item {itemCls}" tabIndex="-1">',
				'<label for="{id}" style="{labelStyle}" class="x-form-item-label">{label}{labelSeparator}</label>',
				'<div class="x-form-element" id="x-form-el-{id}" style="{elementStyle}">',
				'</div><div class="{clearCls}"></div>', "</div>");
		a.disableFormats = true;
		return a.compile()
	})(),
	destroy : Ext.emptyFn
};
Ext.Container.LAYOUTS.auto = Ext.layout.ContainerLayout;
Ext.layout.FitLayout = Ext.extend(Ext.layout.ContainerLayout, {
	monitorResize : true,
	onLayout : function(a, c) {
		Ext.layout.FitLayout.superclass.onLayout.call(this, a, c);
		if (!this.container.collapsed) {
			var b = (Ext.isIE6 && Ext.isStrict && c.dom == document.body) ? c.getViewSize() : c.getStyleSize();
			this.setItemSize(this.activeItem || a.items.itemAt(0), b)
		}
	},
	setItemSize : function(b, a) {
		if (b && a.height > 0) {
			b.setSize(a)
		}
	}
});
Ext.Container.LAYOUTS.fit = Ext.layout.FitLayout;
Ext.layout.CardLayout = Ext.extend(Ext.layout.FitLayout, {
	deferredRender : false,
	layoutOnCardChange : false,
	renderHidden : true,
	constructor : function(a) {
		Ext.layout.CardLayout.superclass.constructor.call(this, a);
		this.forceLayout = (this.deferredRender === false)
	},
	setActiveItem : function(a) {
		a = this.container.getComponent(a);
		if (this.activeItem != a) {
			if (this.activeItem) {
				this.activeItem.hide()
			}
			this.activeItem = a;
			a.show();
			this.container.doLayout();
			if (this.layoutOnCardChange && a.doLayout) {
				a.doLayout()
			}
		}
	},
	renderAll : function(a, b) {
		if (this.deferredRender) {
			this.renderItem(this.activeItem, undefined, b)
		} else {
			Ext.layout.CardLayout.superclass.renderAll.call(this, a, b)
		}
	}
});
Ext.Container.LAYOUTS.card = Ext.layout.CardLayout;
Ext.layout.AnchorLayout = Ext.extend(Ext.layout.ContainerLayout, {
	monitorResize : true,
	getAnchorViewSize : function(a, b) {
		return b.dom == document.body ? b.getViewSize() : b.getStyleSize()
	},
	onLayout : function(j, m) {
		Ext.layout.AnchorLayout.superclass.onLayout.call(this, j, m);
		var s = this.getAnchorViewSize(j, m);
		var q = s.width, g = s.height;
		if (q < 20 && g < 20) {
			return
		}
		var d, o;
		if (j.anchorSize) {
			if (typeof j.anchorSize == "number") {
				d = j.anchorSize
			} else {
				d = j.anchorSize.width;
				o = j.anchorSize.height
			}
		} else {
			d = j.initialConfig.width;
			o = j.initialConfig.height
		}
		var l = j.items.items, k = l.length, f, n, p, e, b;
		for (f = 0; f < k; f++) {
			n = l[f];
			if (n.anchor) {
				p = n.anchorSpec;
				if (!p) {
					var r = n.anchor.split(" ");
					n.anchorSpec = p = {
						right : this.parseAnchor(r[0], n.initialConfig.width, d),
						bottom : this.parseAnchor(r[1], n.initialConfig.height, o)
					}
				}
				e = p.right ? this.adjustWidthAnchor(p.right(q), n) : undefined;
				b = p.bottom ? this.adjustHeightAnchor(p.bottom(g), n) : undefined;
				if (e || b) {
					n.setSize(e || undefined, b || undefined)
				}
			}
		}
	},
	parseAnchor : function(c, g, b) {
		if (c && c != "none") {
			var e;
			if (/^(r|right|b|bottom)$/i.test(c)) {
				var f = b - g;
				return function(a) {
					if (a !== e) {
						e = a;
						return a - f
					}
				}
			} else {
				if (c.indexOf("%") != -1) {
					var d = parseFloat(c.replace("%", "")) * 0.01;
					return function(a) {
						if (a !== e) {
							e = a;
							return Math.floor(a * d)
						}
					}
				} else {
					c = parseInt(c, 10);
					if (!isNaN(c)) {
						return function(a) {
							if (a !== e) {
								e = a;
								return a + c
							}
						}
					}
				}
			}
		}
		return false
	},
	adjustWidthAnchor : function(b, a) {
		return b
	},
	adjustHeightAnchor : function(b, a) {
		return b
	}
});
Ext.Container.LAYOUTS.anchor = Ext.layout.AnchorLayout;
Ext.layout.ColumnLayout = Ext.extend(Ext.layout.ContainerLayout, {
	monitorResize : true,
	extraCls : "x-column",
	scrollOffset : 0,
	isValidParent : function(b, a) {
		return (b.getPositionEl ? b.getPositionEl() : b.getEl()).dom.parentNode == this.innerCt.dom
	},
	onLayout : function(d, g) {
		var e = d.items.items, f = e.length, j, a;
		if (!this.innerCt) {
			g.addClass("x-column-layout-ct");
			this.innerCt = g.createChild({
				cls : "x-column-inner"
			});
			this.innerCt.createChild({
				cls : "x-clear"
			})
		}
		this.renderAll(d, this.innerCt);
		var m = Ext.isIE && g.dom != Ext.getBody().dom ? g.getStyleSize() : g.getViewSize();
		if (m.width < 1 && m.height < 1) {
			return
		}
		var k = m.width - g.getPadding("lr") - this.scrollOffset, b = m.height - g.getPadding("tb"), l = k;
		this.innerCt.setWidth(k);
		for (a = 0; a < f; a++) {
			j = e[a];
			if (!j.columnWidth) {
				l -= (j.getSize().width + j.getEl().getMargins("lr"))
			}
		}
		l = l < 0 ? 0 : l;
		for (a = 0; a < f; a++) {
			j = e[a];
			if (j.columnWidth) {
				j.setSize(Math.floor(j.columnWidth * l) - j.getEl().getMargins("lr"))
			}
		}
	}
});
Ext.Container.LAYOUTS.column = Ext.layout.ColumnLayout;
Ext.layout.BorderLayout = Ext.extend(Ext.layout.ContainerLayout, {
	monitorResize : true,
	rendered : false,
	onLayout : function(d, G) {
		var f;
		if (!this.rendered) {
			G.addClass("x-border-layout-ct");
			var u = d.items.items;
			f = [];
			for ( var z = 0, A = u.length; z < A; z++) {
				var D = u[z];
				var k = D.region;
				if (D.collapsed) {
					f.push(D)
				}
				D.collapsed = false;
				if (!D.rendered) {
					D.cls = D.cls ? D.cls + " x-border-panel" : "x-border-panel";
					D.render(G, z)
				}
				this[k] = k != "center" && D.split ? new Ext.layout.BorderLayout.SplitRegion(this, D.initialConfig, k)
						: new Ext.layout.BorderLayout.Region(this, D.initialConfig, k);
				this[k].render(G, D)
			}
			this.rendered = true
		}
		var t = G.getViewSize();
		if (t.width < 20 || t.height < 20) {
			if (f) {
				this.restoreCollapsed = f
			}
			return
		} else {
			if (this.restoreCollapsed) {
				f = this.restoreCollapsed;
				delete this.restoreCollapsed
			}
		}
		var q = t.width, B = t.height;
		var p = q, y = B, l = 0, o = 0;
		var v = this.north, r = this.south, j = this.west, C = this.east, D = this.center;
		if (!D && Ext.layout.BorderLayout.WARN !== false) {
			throw "No center region defined in BorderLayout " + d.id
		}
		if (v && v.isVisible()) {
			var F = v.getSize();
			var x = v.getMargins();
			F.width = q - (x.left + x.right);
			F.x = x.left;
			F.y = x.top;
			l = F.height + F.y + x.bottom;
			y -= l;
			v.applyLayout(F)
		}
		if (r && r.isVisible()) {
			var F = r.getSize();
			var x = r.getMargins();
			F.width = q - (x.left + x.right);
			F.x = x.left;
			var E = (F.height + x.top + x.bottom);
			F.y = B - E + x.top;
			y -= E;
			r.applyLayout(F)
		}
		if (j && j.isVisible()) {
			var F = j.getSize();
			var x = j.getMargins();
			F.height = y - (x.top + x.bottom);
			F.x = x.left;
			F.y = l + x.top;
			var a = (F.width + x.left + x.right);
			o += a;
			p -= a;
			j.applyLayout(F)
		}
		if (C && C.isVisible()) {
			var F = C.getSize();
			var x = C.getMargins();
			F.height = y - (x.top + x.bottom);
			var a = (F.width + x.left + x.right);
			F.x = q - a + x.left;
			F.y = l + x.top;
			p -= a;
			C.applyLayout(F)
		}
		if (D) {
			var x = D.getMargins();
			var g = {
				x : o + x.left,
				y : l + x.top,
				width : p - (x.left + x.right),
				height : y - (x.top + x.bottom)
			};
			D.applyLayout(g)
		}
		if (f) {
			for ( var z = 0, A = f.length; z < A; z++) {
				f[z].collapse(false)
			}
		}
		if (Ext.isIE && Ext.isStrict) {
			G.repaint()
		}
	},
	destroy : function() {
		var b = [ "north", "south", "east", "west" ];
		for ( var a = 0; a < b.length; a++) {
			var c = this[b[a]];
			if (c) {
				if (c.destroy) {
					c.destroy()
				} else {
					if (c.split) {
						c.split.destroy(true)
					}
				}
			}
		}
		Ext.layout.BorderLayout.superclass.destroy.call(this)
	}
});
Ext.layout.BorderLayout.Region = function(b, a, c) {
	Ext.apply(this, a);
	this.layout = b;
	this.position = c;
	this.state = {};
	if (typeof this.margins == "string") {
		this.margins = this.layout.parseMargins(this.margins)
	}
	this.margins = Ext.applyIf(this.margins || {}, this.defaultMargins);
	if (this.collapsible) {
		if (typeof this.cmargins == "string") {
			this.cmargins = this.layout.parseMargins(this.cmargins)
		}
		if (this.collapseMode == "mini" && !this.cmargins) {
			this.cmargins = {
				left : 0,
				top : 0,
				right : 0,
				bottom : 0
			}
		} else {
			this.cmargins = Ext.applyIf(this.cmargins || {}, c == "north" || c == "south" ? this.defaultNSCMargins
					: this.defaultEWCMargins)
		}
	}
};
Ext.layout.BorderLayout.Region.prototype = {
	collapsible : false,
	split : false,
	floatable : true,
	minWidth : 50,
	minHeight : 50,
	defaultMargins : {
		left : 0,
		top : 0,
		right : 0,
		bottom : 0
	},
	defaultNSCMargins : {
		left : 5,
		top : 5,
		right : 5,
		bottom : 5
	},
	defaultEWCMargins : {
		left : 5,
		top : 0,
		right : 5,
		bottom : 0
	},
	floatingZIndex : 100,
	isCollapsed : false,
	render : function(b, c) {
		this.panel = c;
		c.el.enableDisplayMode();
		this.targetEl = b;
		this.el = c.el;
		var a = c.getState, d = this.position;
		c.getState = function() {
			return Ext.apply(a.call(c) || {}, this.state)
		}.createDelegate(this);
		if (d != "center") {
			c.allowQueuedExpand = false;
			c.on({
				beforecollapse : this.beforeCollapse,
				collapse : this.onCollapse,
				beforeexpand : this.beforeExpand,
				expand : this.onExpand,
				hide : this.onHide,
				show : this.onShow,
				scope : this
			});
			if (this.collapsible || this.floatable) {
				c.collapseEl = "el";
				c.slideAnchor = this.getSlideAnchor()
			}
			if (c.tools && c.tools.toggle) {
				c.tools.toggle.addClass("x-tool-collapse-" + d);
				c.tools.toggle.addClassOnOver("x-tool-collapse-" + d + "-over")
			}
		}
	},
	getCollapsedEl : function() {
		if (!this.collapsedEl) {
			if (!this.toolTemplate) {
				var b = new Ext.Template('<div class="x-tool x-tool-{id}">&#160;</div>');
				b.disableFormats = true;
				b.compile();
				Ext.layout.BorderLayout.Region.prototype.toolTemplate = b
			}
			this.collapsedEl = this.targetEl.createChild({
				cls : "x-layout-collapsed x-layout-collapsed-" + this.position,
				id : this.panel.id + "-xcollapsed"
			});
			this.collapsedEl.enableDisplayMode("block");
			if (this.collapseMode == "mini") {
				this.collapsedEl.addClass("x-layout-cmini-" + this.position);
				this.miniCollapsedEl = this.collapsedEl.createChild({
					cls : "x-layout-mini x-layout-mini-" + this.position,
					html : "&#160;"
				});
				this.miniCollapsedEl.addClassOnOver("x-layout-mini-over");
				this.collapsedEl.addClassOnOver("x-layout-collapsed-over");
				this.collapsedEl.on("click", this.onExpandClick, this, {
					stopEvent : true
				})
			} else {
				if (this.collapsible !== false && !this.hideCollapseTool) {
					var a = this.toolTemplate.append(this.collapsedEl.dom, {
						id : "expand-" + this.position
					}, true);
					a.addClassOnOver("x-tool-expand-" + this.position + "-over");
					a.on("click", this.onExpandClick, this, {
						stopEvent : true
					})
				}
				if (this.floatable !== false || this.titleCollapse) {
					this.collapsedEl.addClassOnOver("x-layout-collapsed-over");
					this.collapsedEl.on("click", this[this.floatable ? "collapseClick" : "onExpandClick"], this)
				}
			}
		}
		return this.collapsedEl
	},
	onExpandClick : function(a) {
		if (this.isSlid) {
			this.afterSlideIn();
			this.panel.expand(false)
		} else {
			this.panel.expand()
		}
	},
	onCollapseClick : function(a) {
		this.panel.collapse()
	},
	beforeCollapse : function(b, a) {
		this.lastAnim = a;
		if (this.splitEl) {
			this.splitEl.hide()
		}
		this.getCollapsedEl().show();
		this.panel.el.setStyle("z-index", 100);
		this.isCollapsed = true;
		this.layout.layout()
	},
	onCollapse : function(a) {
		this.panel.el.setStyle("z-index", 1);
		if (this.lastAnim === false || this.panel.animCollapse === false) {
			this.getCollapsedEl().dom.style.visibility = "visible"
		} else {
			this.getCollapsedEl().slideIn(this.panel.slideAnchor, {
				duration : 0.2
			})
		}
		this.state.collapsed = true;
		this.panel.saveState()
	},
	beforeExpand : function(a) {
		var b = this.getCollapsedEl();
		this.el.show();
		if (this.position == "east" || this.position == "west") {
			this.panel.setSize(undefined, b.getHeight())
		} else {
			this.panel.setSize(b.getWidth(), undefined)
		}
		b.hide();
		b.dom.style.visibility = "hidden";
		this.panel.el.setStyle("z-index", this.floatingZIndex)
	},
	onExpand : function() {
		this.isCollapsed = false;
		if (this.splitEl) {
			this.splitEl.show()
		}
		this.layout.layout();
		this.panel.el.setStyle("z-index", 1);
		this.state.collapsed = false;
		this.panel.saveState()
	},
	collapseClick : function(a) {
		if (this.isSlid) {
			a.stopPropagation();
			this.slideIn()
		} else {
			a.stopPropagation();
			this.slideOut()
		}
	},
	onHide : function() {
		if (this.isCollapsed) {
			this.getCollapsedEl().hide()
		} else {
			if (this.splitEl) {
				this.splitEl.hide()
			}
		}
	},
	onShow : function() {
		if (this.isCollapsed) {
			this.getCollapsedEl().show()
		} else {
			if (this.splitEl) {
				this.splitEl.show()
			}
		}
	},
	isVisible : function() {
		return !this.panel.hidden
	},
	getMargins : function() {
		return this.isCollapsed && this.cmargins ? this.cmargins : this.margins
	},
	getSize : function() {
		return this.isCollapsed ? this.getCollapsedEl().getSize() : this.panel.getSize()
	},
	setPanel : function(a) {
		this.panel = a
	},
	getMinWidth : function() {
		return this.minWidth
	},
	getMinHeight : function() {
		return this.minHeight
	},
	applyLayoutCollapsed : function(a) {
		var b = this.getCollapsedEl();
		b.setLeftTop(a.x, a.y);
		b.setSize(a.width, a.height)
	},
	applyLayout : function(a) {
		if (this.isCollapsed) {
			this.applyLayoutCollapsed(a)
		} else {
			this.panel.setPosition(a.x, a.y);
			this.panel.setSize(a.width, a.height)
		}
	},
	beforeSlide : function() {
		this.panel.beforeEffect()
	},
	afterSlide : function() {
		this.panel.afterEffect()
	},
	initAutoHide : function() {
		if (this.autoHide !== false) {
			if (!this.autoHideHd) {
				var a = new Ext.util.DelayedTask(this.slideIn, this);
				this.autoHideHd = {
					mouseout : function(b) {
						if (!b.within(this.el, true)) {
							a.delay(500)
						}
					},
					mouseover : function(b) {
						a.cancel()
					},
					scope : this
				}
			}
			this.el.on(this.autoHideHd)
		}
	},
	clearAutoHide : function() {
		if (this.autoHide !== false) {
			this.el.un("mouseout", this.autoHideHd.mouseout);
			this.el.un("mouseover", this.autoHideHd.mouseover)
		}
	},
	clearMonitor : function() {
		Ext.getDoc().un("click", this.slideInIf, this)
	},
	slideOut : function() {
		if (this.isSlid || this.el.hasActiveFx()) {
			return
		}
		this.isSlid = true;
		var a = this.panel.tools;
		if (a && a.toggle) {
			a.toggle.hide()
		}
		this.el.show();
		if (this.position == "east" || this.position == "west") {
			this.panel.setSize(undefined, this.collapsedEl.getHeight())
		} else {
			this.panel.setSize(this.collapsedEl.getWidth(), undefined)
		}
		this.restoreLT = [ this.el.dom.style.left, this.el.dom.style.top ];
		this.el.alignTo(this.collapsedEl, this.getCollapseAnchor());
		this.el.setStyle("z-index", this.floatingZIndex + 2);
		this.panel.el.replaceClass("x-panel-collapsed", "x-panel-floating");
		if (this.animFloat !== false) {
			this.beforeSlide();
			this.el.slideIn(this.getSlideAnchor(), {
				callback : function() {
					this.afterSlide();
					this.initAutoHide();
					Ext.getDoc().on("click", this.slideInIf, this)
				},
				scope : this,
				block : true
			})
		} else {
			this.initAutoHide();
			Ext.getDoc().on("click", this.slideInIf, this)
		}
	},
	afterSlideIn : function() {
		this.clearAutoHide();
		this.isSlid = false;
		this.clearMonitor();
		this.el.setStyle("z-index", "");
		this.panel.el.replaceClass("x-panel-floating", "x-panel-collapsed");
		this.el.dom.style.left = this.restoreLT[0];
		this.el.dom.style.top = this.restoreLT[1];
		var a = this.panel.tools;
		if (a && a.toggle) {
			a.toggle.show()
		}
	},
	slideIn : function(a) {
		if (!this.isSlid || this.el.hasActiveFx()) {
			Ext.callback(a);
			return
		}
		this.isSlid = false;
		if (this.animFloat !== false) {
			this.beforeSlide();
			this.el.slideOut(this.getSlideAnchor(), {
				callback : function() {
					this.el.hide();
					this.afterSlide();
					this.afterSlideIn();
					Ext.callback(a)
				},
				scope : this,
				block : true
			})
		} else {
			this.el.hide();
			this.afterSlideIn()
		}
	},
	slideInIf : function(a) {
		if (!a.within(this.el)) {
			this.slideIn()
		}
	},
	anchors : {
		west : "left",
		east : "right",
		north : "top",
		south : "bottom"
	},
	sanchors : {
		west : "l",
		east : "r",
		north : "t",
		south : "b"
	},
	canchors : {
		west : "tl-tr",
		east : "tr-tl",
		north : "tl-bl",
		south : "bl-tl"
	},
	getAnchor : function() {
		return this.anchors[this.position]
	},
	getCollapseAnchor : function() {
		return this.canchors[this.position]
	},
	getSlideAnchor : function() {
		return this.sanchors[this.position]
	},
	getAlignAdj : function() {
		var a = this.cmargins;
		switch (this.position) {
		case "west":
			return [ 0, 0 ];
			break;
		case "east":
			return [ 0, 0 ];
			break;
		case "north":
			return [ 0, 0 ];
			break;
		case "south":
			return [ 0, 0 ];
			break
		}
	},
	getExpandAdj : function() {
		var b = this.collapsedEl, a = this.cmargins;
		switch (this.position) {
		case "west":
			return [ -(a.right + b.getWidth() + a.left), 0 ];
			break;
		case "east":
			return [ a.right + b.getWidth() + a.left, 0 ];
			break;
		case "north":
			return [ 0, -(a.top + a.bottom + b.getHeight()) ];
			break;
		case "south":
			return [ 0, a.top + a.bottom + b.getHeight() ];
			break
		}
	}
};
Ext.layout.BorderLayout.SplitRegion = function(b, a, c) {
	Ext.layout.BorderLayout.SplitRegion.superclass.constructor.call(this, b, a, c);
	this.applyLayout = this.applyFns[c]
};
Ext.extend(Ext.layout.BorderLayout.SplitRegion, Ext.layout.BorderLayout.Region, {
	splitTip : "Drag to resize.",
	collapsibleSplitTip : "Drag to resize. Double click to hide.",
	useSplitTips : false,
	splitSettings : {
		north : {
			orientation : Ext.SplitBar.VERTICAL,
			placement : Ext.SplitBar.TOP,
			maxFn : "getVMaxSize",
			minProp : "minHeight",
			maxProp : "maxHeight"
		},
		south : {
			orientation : Ext.SplitBar.VERTICAL,
			placement : Ext.SplitBar.BOTTOM,
			maxFn : "getVMaxSize",
			minProp : "minHeight",
			maxProp : "maxHeight"
		},
		east : {
			orientation : Ext.SplitBar.HORIZONTAL,
			placement : Ext.SplitBar.RIGHT,
			maxFn : "getHMaxSize",
			minProp : "minWidth",
			maxProp : "maxWidth"
		},
		west : {
			orientation : Ext.SplitBar.HORIZONTAL,
			placement : Ext.SplitBar.LEFT,
			maxFn : "getHMaxSize",
			minProp : "minWidth",
			maxProp : "maxWidth"
		}
	},
	applyFns : {
		west : function(c) {
			if (this.isCollapsed) {
				return this.applyLayoutCollapsed(c)
			}
			var d = this.splitEl.dom, b = d.style;
			this.panel.setPosition(c.x, c.y);
			var a = d.offsetWidth;
			b.left = (c.x + c.width - a) + "px";
			b.top = (c.y) + "px";
			b.height = Math.max(0, c.height) + "px";
			this.panel.setSize(c.width - a, c.height)
		},
		east : function(c) {
			if (this.isCollapsed) {
				return this.applyLayoutCollapsed(c)
			}
			var d = this.splitEl.dom, b = d.style;
			var a = d.offsetWidth;
			this.panel.setPosition(c.x + a, c.y);
			b.left = (c.x) + "px";
			b.top = (c.y) + "px";
			b.height = Math.max(0, c.height) + "px";
			this.panel.setSize(c.width - a, c.height)
		},
		north : function(c) {
			if (this.isCollapsed) {
				return this.applyLayoutCollapsed(c)
			}
			var d = this.splitEl.dom, b = d.style;
			var a = d.offsetHeight;
			this.panel.setPosition(c.x, c.y);
			b.left = (c.x) + "px";
			b.top = (c.y + c.height - a) + "px";
			b.width = Math.max(0, c.width) + "px";
			this.panel.setSize(c.width, c.height - a)
		},
		south : function(c) {
			if (this.isCollapsed) {
				return this.applyLayoutCollapsed(c)
			}
			var d = this.splitEl.dom, b = d.style;
			var a = d.offsetHeight;
			this.panel.setPosition(c.x, c.y + a);
			b.left = (c.x) + "px";
			b.top = (c.y) + "px";
			b.width = Math.max(0, c.width) + "px";
			this.panel.setSize(c.width, c.height - a)
		}
	},
	render : function(a, c) {
		Ext.layout.BorderLayout.SplitRegion.superclass.render.call(this, a, c);
		var d = this.position;
		this.splitEl = a.createChild({
			cls : "x-layout-split x-layout-split-" + d,
			html : "&#160;",
			id : this.panel.id + "-xsplit"
		});
		if (this.collapseMode == "mini") {
			this.miniSplitEl = this.splitEl.createChild({
				cls : "x-layout-mini x-layout-mini-" + d,
				html : "&#160;"
			});
			this.miniSplitEl.addClassOnOver("x-layout-mini-over");
			this.miniSplitEl.on("click", this.onCollapseClick, this, {
				stopEvent : true
			})
		}
		var b = this.splitSettings[d];
		this.split = new Ext.SplitBar(this.splitEl.dom, c.el, b.orientation);
		this.split.tickSize = this.tickSize;
		this.split.placement = b.placement;
		this.split.getMaximumSize = this[b.maxFn].createDelegate(this);
		this.split.minSize = this.minSize || this[b.minProp];
		this.split.on("beforeapply", this.onSplitMove, this);
		this.split.useShim = this.useShim === true;
		this.maxSize = this.maxSize || this[b.maxProp];
		if (c.hidden) {
			this.splitEl.hide()
		}
		if (this.useSplitTips) {
			this.splitEl.dom.title = this.collapsible ? this.collapsibleSplitTip : this.splitTip
		}
		if (this.collapsible) {
			this.splitEl.on("dblclick", this.onCollapseClick, this)
		}
	},
	getSize : function() {
		if (this.isCollapsed) {
			return this.collapsedEl.getSize()
		}
		var a = this.panel.getSize();
		if (this.position == "north" || this.position == "south") {
			a.height += this.splitEl.dom.offsetHeight
		} else {
			a.width += this.splitEl.dom.offsetWidth
		}
		return a
	},
	getHMaxSize : function() {
		var b = this.maxSize || 10000;
		var a = this.layout.center;
		return Math.min(b, (this.el.getWidth() + a.el.getWidth()) - a.getMinWidth())
	},
	getVMaxSize : function() {
		var b = this.maxSize || 10000;
		var a = this.layout.center;
		return Math.min(b, (this.el.getHeight() + a.el.getHeight()) - a.getMinHeight())
	},
	onSplitMove : function(b, a) {
		var c = this.panel.getSize();
		this.lastSplitSize = a;
		if (this.position == "north" || this.position == "south") {
			this.panel.setSize(c.width, a);
			this.state.height = a
		} else {
			this.panel.setSize(a, c.height);
			this.state.width = a
		}
		this.layout.layout();
		this.panel.saveState();
		return false
	},
	getSplitBar : function() {
		return this.split
	},
	destroy : function() {
		Ext.destroy(this.miniSplitEl, this.split, this.splitEl)
	}
});
Ext.Container.LAYOUTS.border = Ext.layout.BorderLayout;
Ext.layout.FormLayout = Ext.extend(Ext.layout.AnchorLayout,
		{
			labelSeparator : ":",
			setContainer : function(a) {
				Ext.layout.FormLayout.superclass.setContainer.call(this, a);
				if (a.labelAlign) {
					a.addClass("x-form-label-" + a.labelAlign)
				}
				if (a.hideLabels) {
					this.labelStyle = "display:none";
					this.elementStyle = "padding-left:0;";
					this.labelAdjust = 0
				} else {
					this.labelSeparator = a.labelSeparator || this.labelSeparator;
					a.labelWidth = a.labelWidth || 100;
					if (typeof a.labelWidth == "number") {
						var b = (typeof a.labelPad == "number" ? a.labelPad : 5);
						this.labelAdjust = a.labelWidth + b;
						this.labelStyle = "width:" + a.labelWidth + "px;";
						this.elementStyle = "padding-left:" + (a.labelWidth + b) + "px"
					}
					if (a.labelAlign == "top") {
						this.labelStyle = "width:auto;";
						this.labelAdjust = 0;
						this.elementStyle = "padding-left:0;"
					}
				}
			},
			getLabelStyle : function(e) {
				var b = "", c = [ this.labelStyle, e ];
				for ( var d = 0, a = c.length; d < a; ++d) {
					if (c[d]) {
						b += c[d];
						if (b.substr(-1, 1) != ";") {
							b += ";"
						}
					}
				}
				return b
			},
			renderItem : function(e, a, d) {
				if (e && !e.rendered && (e.isFormField || e.fieldLabel) && e.inputType != "hidden") {
					var b = this.getTemplateArgs(e);
					if (typeof a == "number") {
						a = d.dom.childNodes[a] || null
					}
					if (a) {
						this.fieldTpl.insertBefore(a, b)
					} else {
						this.fieldTpl.append(d, b)
					}
					e.render("x-form-el-" + e.id)
				} else {
					Ext.layout.FormLayout.superclass.renderItem.apply(this, arguments)
				}
			},
			getTemplateArgs : function(b) {
				var a = !b.fieldLabel || b.hideLabel;
				return {
					id : b.id,
					label : b.fieldLabel,
					labelStyle : b.labelStyle || this.labelStyle || "",
					elementStyle : this.elementStyle || "",
					labelSeparator : a ? "" : (typeof b.labelSeparator == "undefined" ? this.labelSeparator
							: b.labelSeparator),
					itemCls : (b.itemCls || this.container.itemCls || "") + (b.hideLabel ? " x-hide-label" : ""),
					clearCls : b.clearCls || "x-form-clear-left"
				}
			},
			adjustWidthAnchor : function(b, a) {
				return b - (a.isFormField || a.fieldLabel ? (a.hideLabel ? 0 : this.labelAdjust) : 0)
			},
			isValidParent : function(b, a) {
				return true
			}
		});
Ext.Container.LAYOUTS.form = Ext.layout.FormLayout;
Ext.layout.AccordionLayout = Ext.extend(Ext.layout.FitLayout, {
	fill : true,
	autoWidth : true,
	titleCollapse : true,
	hideCollapseTool : false,
	collapseFirst : false,
	animate : false,
	sequence : false,
	activeOnTop : false,
	renderItem : function(a) {
		if (this.animate === false) {
			a.animCollapse = false
		}
		a.collapsible = true;
		if (this.autoWidth) {
			a.autoWidth = true
		}
		if (this.titleCollapse) {
			a.titleCollapse = true
		}
		if (this.hideCollapseTool) {
			a.hideCollapseTool = true
		}
		if (this.collapseFirst !== undefined) {
			a.collapseFirst = this.collapseFirst
		}
		if (!this.activeItem && !a.collapsed) {
			this.activeItem = a
		} else {
			if (this.activeItem && this.activeItem != a) {
				a.collapsed = true
			}
		}
		Ext.layout.AccordionLayout.superclass.renderItem.apply(this, arguments);
		a.header.addClass("x-accordion-hd");
		a.on("beforeexpand", this.beforeExpand, this)
	},
	beforeExpand : function(c, b) {
		var a = this.activeItem;
		if (a) {
			if (this.sequence) {
				delete this.activeItem;
				if (!a.collapsed) {
					a.collapse({
						callback : function() {
							c.expand(b || true)
						},
						scope : this
					});
					return false
				}
			} else {
				a.collapse(this.animate)
			}
		}
		this.activeItem = c;
		if (this.activeOnTop) {
			c.el.dom.parentNode.insertBefore(c.el.dom, c.el.dom.parentNode.firstChild)
		}
		this.layout()
	},
	setItemSize : function(c, b) {
		if (this.fill && c) {
			var a = 0;
			this.container.items.each(function(d) {
				if (d != c) {
					a += d.header.getHeight()
				}
			});
			b.height -= a;
			c.setSize(b)
		}
	},
	setActiveItem : function(a) {
		a = this.container.getComponent(a);
		if (this.activeItem != a) {
			if (a.rendered && a.collapsed) {
				a.expand()
			} else {
				this.activeItem = a
			}
		}
	}
});
Ext.Container.LAYOUTS.accordion = Ext.layout.AccordionLayout;
Ext.layout.Accordion = Ext.layout.AccordionLayout;
Ext.layout.TableLayout = Ext.extend(Ext.layout.ContainerLayout, {
	monitorResize : false,
	tableAttrs : null,
	setContainer : function(a) {
		Ext.layout.TableLayout.superclass.setContainer.call(this, a);
		this.currentRow = 0;
		this.currentColumn = 0;
		this.cells = []
	},
	onLayout : function(d, f) {
		var e = d.items.items, a = e.length, g, b;
		if (!this.table) {
			f.addClass("x-table-layout-ct");
			this.table = f.createChild(Ext.apply({
				tag : "table",
				cls : "x-table-layout",
				cellspacing : 0,
				cn : {
					tag : "tbody"
				}
			}, this.tableAttrs), null, true)
		}
		this.renderAll(d, f)
	},
	getRow : function(a) {
		var b = this.table.tBodies[0].childNodes[a];
		if (!b) {
			b = document.createElement("tr");
			this.table.tBodies[0].appendChild(b)
		}
		return b
	},
	getNextCell : function(i) {
		var a = this.getNextNonSpan(this.currentColumn, this.currentRow);
		var f = this.currentColumn = a[0], e = this.currentRow = a[1];
		for ( var h = e; h < e + (i.rowspan || 1); h++) {
			if (!this.cells[h]) {
				this.cells[h] = []
			}
			for ( var d = f; d < f + (i.colspan || 1); d++) {
				this.cells[h][d] = true
			}
		}
		var g = document.createElement("td");
		if (i.cellId) {
			g.id = i.cellId
		}
		var b = "x-table-layout-cell";
		if (i.cellCls) {
			b += " " + i.cellCls
		}
		g.className = b;
		if (i.colspan) {
			g.colSpan = i.colspan
		}
		if (i.rowspan) {
			g.rowSpan = i.rowspan
		}
		this.getRow(e).appendChild(g);
		return g
	},
	getNextNonSpan : function(a, c) {
		var b = this.columns;
		while ((b && a >= b) || (this.cells[c] && this.cells[c][a])) {
			if (b && a >= b) {
				c++;
				a = 0
			} else {
				a++
			}
		}
		return [ a, c ]
	},
	renderItem : function(e, a, d) {
		if (e && !e.rendered) {
			e.render(this.getNextCell(e));
			if (this.extraCls) {
				var b = e.getPositionEl ? e.getPositionEl() : e;
				b.addClass(this.extraCls)
			}
		}
	},
	isValidParent : function(b, a) {
		return true
	}
});
Ext.Container.LAYOUTS.table = Ext.layout.TableLayout;
Ext.layout.AbsoluteLayout = Ext.extend(Ext.layout.AnchorLayout, {
	extraCls : "x-abs-layout-item",
	onLayout : function(a, b) {
		b.position();
		this.paddingLeft = b.getPadding("l");
		this.paddingTop = b.getPadding("t");
		Ext.layout.AbsoluteLayout.superclass.onLayout.call(this, a, b)
	},
	adjustWidthAnchor : function(b, a) {
		return b ? b - a.getPosition(true)[0] + this.paddingLeft : b
	},
	adjustHeightAnchor : function(b, a) {
		return b ? b - a.getPosition(true)[1] + this.paddingTop : b
	}
});
Ext.Container.LAYOUTS.absolute = Ext.layout.AbsoluteLayout;
Ext.layout.BoxLayout = Ext.extend(Ext.layout.ContainerLayout, {
	defaultMargins : {
		left : 0,
		top : 0,
		right : 0,
		bottom : 0
	},
	padding : "0",
	pack : "start",
	monitorResize : true,
	scrollOffset : 0,
	extraCls : "x-box-item",
	ctCls : "x-box-layout-ct",
	innerCls : "x-box-inner",
	isValidParent : function(b, a) {
		return b.getEl().dom.parentNode == this.innerCt.dom
	},
	onLayout : function(e, h) {
		var f = e.items.items, b = f.length, j, d, g = b - 1, a;
		if (!this.innerCt) {
			h.addClass(this.ctCls);
			this.innerCt = h.createChild({
				cls : this.innerCls
			});
			this.padding = this.parseMargins(this.padding)
		}
		this.renderAll(e, this.innerCt)
	},
	renderItem : function(a) {
		if (typeof a.margins == "string") {
			a.margins = this.parseMargins(a.margins)
		} else {
			if (!a.margins) {
				a.margins = this.defaultMargins
			}
		}
		Ext.layout.BoxLayout.superclass.renderItem.apply(this, arguments)
	},
	getTargetSize : function(a) {
		return (Ext.isIE6 && Ext.isStrict && a.dom == document.body) ? a.getStyleSize() : a.getViewSize()
	},
	getItems : function(b) {
		var a = [];
		b.items.each(function(d) {
			if (d.isVisible()) {
				a.push(d)
			}
		});
		return a
	}
});
Ext.layout.VBoxLayout = Ext
		.extend(
				Ext.layout.BoxLayout,
				{
					align : "left",
					onLayout : function(e, D) {
						Ext.layout.VBoxLayout.superclass.onLayout.call(this, e, D);
						var f = this.getItems(e), i, k, p, q = this.getTargetSize(D), j = q.width - D.getPadding("lr")
								- this.scrollOffset, x = q.height - D.getPadding("tb"), r = this.padding.left, m = this.padding.top, g = this.pack == "start", u = [
								"stretch", "stretchmax" ].indexOf(this.align) == -1, A = j
								- (this.padding.left + this.padding.right), n = 0, z = 0, s = 0, B = 0, d = 0;
						Ext.each(f, function(h) {
							i = h.margins;
							s += h.flex || 0;
							k = h.getHeight();
							p = i.top + i.bottom;
							n += k + p;
							B += p + (h.flex ? 0 : k);
							z = Math.max(z, h.getWidth() + i.left + i.right)
						});
						n = x - n - this.padding.top - this.padding.bottom;
						var a = z + this.padding.left + this.padding.right;
						switch (this.align) {
						case "stretch":
							this.innerCt.setSize(j, x);
							break;
						case "stretchmax":
						case "left":
							this.innerCt.setSize(a, x);
							break;
						case "center":
							this.innerCt.setSize(j = Math.max(j, a), x);
							break
						}
						var c = Math.max(0, x - this.padding.top - this.padding.bottom - B), C = c, y = [], v = [], o = 0, b = Math
								.max(0, j - this.padding.left - this.padding.right);
						Ext.each(f, function(h) {
							if (g && h.flex) {
								k = Math.floor(c * (h.flex / s));
								C -= k;
								y.push(k)
							}
						});
						if (this.pack == "center") {
							m += n ? n / 2 : 0
						} else {
							if (this.pack == "end") {
								m += n
							}
						}
						Ext.each(f, function(h) {
							i = h.margins;
							m += i.top;
							h.setPosition(r + i.left, m);
							if (g && h.flex) {
								k = Math.max(0, y[o++] + (C-- > 0 ? 1 : 0));
								if (u) {
									v.push(h.getWidth())
								}
								h.setSize(b, k)
							} else {
								k = h.getHeight()
							}
							m += k + i.bottom
						});
						o = 0;
						Ext.each(f, function(l) {
							i = l.margins;
							if (this.align == "stretch") {
								l.setWidth((A - (i.left + i.right)).constrain(l.minWidth || 0, l.maxWidth || 1000000))
							} else {
								if (this.align == "stretchmax") {
									l.setWidth((z - (i.left + i.right)).constrain(l.minWidth || 0,
											l.maxWidth || 1000000))
								} else {
									if (this.align == "center") {
										var h = b - (l.getWidth() + i.left + i.right);
										if (h > 0) {
											l.setPosition(r + i.left + (h / 2), l.y)
										}
									}
									if (g && l.flex) {
										l.setWidth(v[o++])
									}
								}
							}
						}, this)
					}
				});
Ext.Container.LAYOUTS.vbox = Ext.layout.VBoxLayout;
Ext.layout.HBoxLayout = Ext
		.extend(
				Ext.layout.BoxLayout,
				{
					align : "top",
					onLayout : function(d, D) {
						Ext.layout.HBoxLayout.superclass.onLayout.call(this, d, D);
						var e = this.getItems(d), i, c, p, q = this.getTargetSize(D), k = q.width - D.getPadding("lr")
								- this.scrollOffset, A = q.height - D.getPadding("tb"), s = this.padding.left, n = this.padding.top, g = this.pack == "start", y = [
								"stretch", "stretchmax" ].indexOf(this.align) == -1, m = A
								- (this.padding.top + this.padding.bottom), B = 0, r = 0, u = 0, b = 0, f = 0;
						Ext.each(e, function(h) {
							i = h.margins;
							u += h.flex || 0;
							c = h.getWidth();
							p = i.left + i.right;
							B += c + p;
							b += p + (h.flex ? 0 : c);
							r = Math.max(r, h.getHeight() + i.top + i.bottom)
						});
						B = k - B - this.padding.left - this.padding.right;
						var x = r + this.padding.top + this.padding.bottom;
						switch (this.align) {
						case "stretch":
							this.innerCt.setSize(k, A);
							break;
						case "stretchmax":
						case "top":
							this.innerCt.setSize(k, x);
							break;
						case "middle":
							this.innerCt.setSize(k, A = Math.max(A, x));
							break
						}
						var v = Math.max(0, k - this.padding.left - this.padding.right - b), C = v, j = [], z = [], o = 0, a = Math
								.max(0, A - this.padding.top - this.padding.bottom);
						Ext.each(e, function(h) {
							if (g && h.flex) {
								c = Math.floor(v * (h.flex / u));
								C -= c;
								j.push(c)
							}
						});
						if (this.pack == "center") {
							s += B ? B / 2 : 0
						} else {
							if (this.pack == "end") {
								s += B
							}
						}
						Ext.each(e, function(h) {
							i = h.margins;
							s += i.left;
							h.setPosition(s, n + i.top);
							if (g && h.flex) {
								c = Math.max(0, j[o++] + (C-- > 0 ? 1 : 0));
								if (y) {
									z.push(h.getHeight())
								}
								h.setSize(c, a)
							} else {
								c = h.getWidth()
							}
							s += c + i.right
						});
						o = 0;
						Ext.each(e, function(t) {
							var h = t.margins;
							if (this.align == "stretch") {
								t.setHeight((m - (h.top + h.bottom))
										.constrain(t.minHeight || 0, t.maxHeight || 1000000))
							} else {
								if (this.align == "stretchmax") {
									t.setHeight((r - (h.top + h.bottom)).constrain(t.minHeight || 0,
											t.maxHeight || 1000000))
								} else {
									if (this.align == "middle") {
										var l = a - (t.getHeight() + h.top + h.bottom);
										if (l > 0) {
											t.setPosition(t.x, n + h.top + (l / 2))
										}
									}
									if (g && t.flex) {
										t.setHeight(z[o++])
									}
								}
							}
						}, this)
					}
				});
Ext.Container.LAYOUTS.hbox = Ext.layout.HBoxLayout;
Ext.Viewport = Ext.extend(Ext.Container, {
	initComponent : function() {
		Ext.Viewport.superclass.initComponent.call(this);
		document.getElementsByTagName("html")[0].className += " x-viewport";
		this.el = Ext.getBody();
		this.el.setHeight = Ext.emptyFn;
		this.el.setWidth = Ext.emptyFn;
		this.el.setSize = Ext.emptyFn;
		this.el.dom.scroll = "no";
		this.allowDomMove = false;
		this.autoWidth = true;
		this.autoHeight = true;
		Ext.EventManager.onWindowResize(this.fireResize, this);
		this.renderTo = this.el
	},
	fireResize : function(a, b) {
		this.fireEvent("resize", this, a, b, a, b)
	}
});
Ext.reg("viewport", Ext.Viewport);
Ext.Panel = Ext.extend(Ext.Container, {
	baseCls : "x-panel",
	collapsedCls : "x-panel-collapsed",
	maskDisabled : true,
	animCollapse : Ext.enableFx,
	headerAsText : true,
	buttonAlign : "right",
	collapsed : false,
	collapseFirst : true,
	minButtonWidth : 75,
	elements : "body",
	preventBodyReset : false,
	toolTarget : "header",
	collapseEl : "bwrap",
	slideAnchor : "t",
	disabledClass : "",
	deferHeight : true,
	expandDefaults : {
		duration : 0.25
	},
	collapseDefaults : {
		duration : 0.25
	},
	initComponent : function() {
		Ext.Panel.superclass.initComponent.call(this);
		this.addEvents("bodyresize", "titlechange", "iconchange", "collapse", "expand", "beforecollapse",
				"beforeexpand", "beforeclose", "close", "activate", "deactivate");
		if (this.unstyled) {
			this.baseCls = "x-plain"
		}
		if (this.tbar) {
			this.elements += ",tbar";
			if (Ext.isObject(this.tbar)) {
				this.topToolbar = this.tbar
			}
			delete this.tbar
		}
		if (this.bbar) {
			this.elements += ",bbar";
			if (Ext.isObject(this.bbar)) {
				this.bottomToolbar = this.bbar
			}
			delete this.bbar
		}
		if (this.header === true) {
			this.elements += ",header";
			delete this.header
		} else {
			if (this.headerCfg || (this.title && this.header !== false)) {
				this.elements += ",header"
			}
		}
		if (this.footerCfg || this.footer === true) {
			this.elements += ",footer";
			delete this.footer
		}
		if (this.buttons) {
			this.elements += ",footer";
			var c = this.buttons;
			this.buttons = [];
			for ( var b = 0, a = c.length; b < a; b++) {
				if (c[b].render) {
					this.buttons.push(c[b])
				} else {
					if (c[b].xtype) {
						this.buttons.push(Ext.create(c[b], "button"))
					} else {
						this.addButton(c[b])
					}
				}
			}
		}
		if (this.fbar) {
			this.elements += ",footer"
		}
		if (this.autoLoad) {
			this.on("render", this.doAutoLoad, this, {
				delay : 10
			})
		}
	},
	createElement : function(a, c) {
		if (this[a]) {
			c.appendChild(this[a].dom);
			return
		}
		if (a === "bwrap" || this.elements.indexOf(a) != -1) {
			if (this[a + "Cfg"]) {
				this[a] = Ext.fly(c).createChild(this[a + "Cfg"])
			} else {
				var b = document.createElement("div");
				b.className = this[a + "Cls"];
				this[a] = Ext.get(c.appendChild(b))
			}
			if (this[a + "CssClass"]) {
				this[a].addClass(this[a + "CssClass"])
			}
			if (this[a + "Style"]) {
				this[a].applyStyles(this[a + "Style"])
			}
		}
	},
	onRender : function(f, e) {
		Ext.Panel.superclass.onRender.call(this, f, e);
		this.createClasses();
		var a = this.el, h = a.dom, j;
		a.addClass(this.baseCls);
		if (h.firstChild) {
			this.header = a.down("." + this.headerCls);
			this.bwrap = a.down("." + this.bwrapCls);
			var i = this.bwrap ? this.bwrap : a;
			this.tbar = i.down("." + this.tbarCls);
			this.body = i.down("." + this.bodyCls);
			this.bbar = i.down("." + this.bbarCls);
			this.footer = i.down("." + this.footerCls);
			this.fromMarkup = true
		}
		if (this.preventBodyReset === true) {
			a.addClass("x-panel-reset")
		}
		if (this.cls) {
			a.addClass(this.cls)
		}
		if (this.buttons) {
			this.elements += ",footer"
		}
		if (this.frame) {
			a.insertHtml("afterBegin", String.format(Ext.Element.boxMarkup, this.baseCls));
			this.createElement("header", h.firstChild.firstChild.firstChild);
			this.createElement("bwrap", h);
			j = this.bwrap.dom;
			var c = h.childNodes[1], b = h.childNodes[2];
			j.appendChild(c);
			j.appendChild(b);
			var k = j.firstChild.firstChild.firstChild;
			this.createElement("tbar", k);
			this.createElement("body", k);
			this.createElement("bbar", k);
			this.createElement("footer", j.lastChild.firstChild.firstChild);
			if (!this.footer) {
				this.bwrap.dom.lastChild.className += " x-panel-nofooter"
			}
		} else {
			this.createElement("header", h);
			this.createElement("bwrap", h);
			j = this.bwrap.dom;
			this.createElement("tbar", j);
			this.createElement("body", j);
			this.createElement("bbar", j);
			this.createElement("footer", j);
			if (!this.header) {
				this.body.addClass(this.bodyCls + "-noheader");
				if (this.tbar) {
					this.tbar.addClass(this.tbarCls + "-noheader")
				}
			}
		}
		if (this.padding !== undefined) {
			this.body.setStyle("padding", this.body.addUnits(this.padding))
		}
		if (this.border === false) {
			this.el.addClass(this.baseCls + "-noborder");
			this.body.addClass(this.bodyCls + "-noborder");
			if (this.header) {
				this.header.addClass(this.headerCls + "-noborder")
			}
			if (this.footer) {
				this.footer.addClass(this.footerCls + "-noborder")
			}
			if (this.tbar) {
				this.tbar.addClass(this.tbarCls + "-noborder")
			}
			if (this.bbar) {
				this.bbar.addClass(this.bbarCls + "-noborder")
			}
		}
		if (this.bodyBorder === false) {
			this.body.addClass(this.bodyCls + "-noborder")
		}
		this.bwrap.enableDisplayMode("block");
		if (this.header) {
			this.header.unselectable();
			if (this.headerAsText) {
				this.header.dom.innerHTML = '<span class="' + this.headerTextCls + '">' + this.header.dom.innerHTML
						+ "</span>";
				if (this.iconCls) {
					this.setIconClass(this.iconCls)
				}
			}
		}
		if (this.floating) {
			this.makeFloating(this.floating)
		}
		if (this.collapsible) {
			this.tools = this.tools ? this.tools.slice(0) : [];
			if (!this.hideCollapseTool) {
				this.tools[this.collapseFirst ? "unshift" : "push"]({
					id : "toggle",
					handler : this.toggleCollapse,
					scope : this
				})
			}
			if (this.titleCollapse && this.header) {
				this.mon(this.header, "click", this.toggleCollapse, this);
				this.header.setStyle("cursor", "pointer")
			}
		}
		if (this.tools) {
			var g = this.tools;
			this.tools = {};
			this.addTool.apply(this, g)
		} else {
			this.tools = {}
		}
		if (this.buttons && this.buttons.length > 0) {
			this.fbar = new Ext.Toolbar({
				items : this.buttons,
				toolbarCls : "x-panel-fbar"
			})
		}
		this.toolbars = [];
		if (this.fbar) {
			this.fbar = Ext.create(this.fbar, "toolbar");
			this.fbar.enableOverflow = false;
			if (this.fbar.items) {
				this.fbar.items.each(function(d) {
					d.minWidth = d.minWidth || this.minButtonWidth
				}, this)
			}
			this.fbar.toolbarCls = "x-panel-fbar";
			var l = this.footer.createChild({
				cls : "x-panel-btns x-panel-btns-" + this.buttonAlign
			});
			this.fbar.ownerCt = this;
			this.fbar.render(l);
			l.createChild({
				cls : "x-clear"
			});
			this.toolbars.push(this.fbar)
		}
		if (this.tbar && this.topToolbar) {
			if (Ext.isArray(this.topToolbar)) {
				this.topToolbar = new Ext.Toolbar(this.topToolbar)
			} else {
				if (!this.topToolbar.events) {
					this.topToolbar = Ext.create(this.topToolbar, "toolbar")
				}
			}
			this.topToolbar.ownerCt = this;
			this.topToolbar.render(this.tbar);
			this.toolbars.push(this.topToolbar)
		}
		if (this.bbar && this.bottomToolbar) {
			if (Ext.isArray(this.bottomToolbar)) {
				this.bottomToolbar = new Ext.Toolbar(this.bottomToolbar)
			} else {
				if (!this.bottomToolbar.events) {
					this.bottomToolbar = Ext.create(this.bottomToolbar, "toolbar")
				}
			}
			this.bottomToolbar.ownerCt = this;
			this.bottomToolbar.render(this.bbar);
			this.toolbars.push(this.bottomToolbar)
		}
		Ext.each(this.toolbars, function(d) {
			d.on({
				scope : this,
				afterlayout : this.syncHeight,
				remove : this.syncHeight
			})
		}, this)
	},
	setIconClass : function(b) {
		var a = this.iconCls;
		this.iconCls = b;
		if (this.rendered && this.header) {
			if (this.frame) {
				this.header.addClass("x-panel-icon");
				this.header.replaceClass(a, this.iconCls)
			} else {
				var d = this.header.dom;
				var c = d.firstChild && String(d.firstChild.tagName).toLowerCase() == "img" ? d.firstChild : null;
				if (c) {
					Ext.fly(c).replaceClass(a, this.iconCls)
				} else {
					Ext.DomHelper.insertBefore(d.firstChild, {
						tag : "img",
						src : Ext.BLANK_IMAGE_URL,
						cls : "x-panel-inline-icon " + this.iconCls
					})
				}
			}
		}
		this.fireEvent("iconchange", this, b, a)
	},
	makeFloating : function(a) {
		this.floating = true;
		this.el = new Ext.Layer(Ext.isObject(a) ? a : {
			shadow : this.shadow !== undefined ? this.shadow : "sides",
			shadowOffset : this.shadowOffset,
			constrain : false,
			shim : this.shim === false ? false : undefined
		}, this.el)
	},
	getTopToolbar : function() {
		return this.topToolbar
	},
	getBottomToolbar : function() {
		return this.bottomToolbar
	},
	addButton : function(a, d, c) {
		var e = {
			handler : d,
			scope : c,
			minWidth : this.minButtonWidth,
			hideParent : true
		};
		if (typeof a == "string") {
			e.text = a
		} else {
			Ext.apply(e, a)
		}
		var b = new Ext.Button(e);
		if (!this.buttons) {
			this.buttons = []
		}
		this.buttons.push(b);
		return b
	},
	addTool : function() {
		if (!this[this.toolTarget]) {
			return
		}
		if (!this.toolTemplate) {
			var g = new Ext.Template('<div class="x-tool x-tool-{id}">&#160;</div>');
			g.disableFormats = true;
			g.compile();
			Ext.Panel.prototype.toolTemplate = g
		}
		for ( var f = 0, d = arguments, c = d.length; f < c; f++) {
			var b = d[f];
			if (!this.tools[b.id]) {
				var h = "x-tool-" + b.id + "-over";
				var e = this.toolTemplate.insertFirst((b.align !== "left") ? this[this.toolTarget]
						: this[this.toolTarget].child("span"), b, true);
				this.tools[b.id] = e;
				e.enableDisplayMode("block");
				this.mon(e, "click", this.createToolHandler(e, b, h, this));
				if (b.on) {
					this.mon(e, b.on)
				}
				if (b.hidden) {
					e.hide()
				}
				if (b.qtip) {
					if (Ext.isObject(b.qtip)) {
						Ext.QuickTips.register(Ext.apply({
							target : e.id
						}, b.qtip))
					} else {
						e.dom.qtip = b.qtip
					}
				}
				e.addClassOnOver(h)
			}
		}
	},
	onLayout : function() {
		if (this.toolbars.length > 0) {
			this.duringLayout = true;
			Ext.each(this.toolbars, function(a) {
				a.doLayout()
			});
			delete this.duringLayout;
			this.syncHeight()
		}
	},
	syncHeight : function() {
		if (!(this.autoHeight || this.duringLayout)) {
			var d = this.lastSize;
			if (d && !Ext.isEmpty(d.height)) {
				var b = d.height, c = this.el.getHeight();
				if (b != "auto" && b != c) {
					var e = this.body, a = e.getHeight();
					c = Math.max(a + b - c, 0);
					if (a > 0 && a != c) {
						e.setHeight(c);
						if (Ext.isIE && c <= 0) {
							return
						}
						var f = e.getSize();
						this.fireEvent("bodyresize", f.width, f.height)
					}
				}
			}
		}
	},
	onShow : function() {
		if (this.floating) {
			return this.el.show()
		}
		Ext.Panel.superclass.onShow.call(this)
	},
	onHide : function() {
		if (this.floating) {
			return this.el.hide()
		}
		Ext.Panel.superclass.onHide.call(this)
	},
	createToolHandler : function(c, a, d, b) {
		return function(f) {
			c.removeClass(d);
			if (a.stopEvent !== false) {
				f.stopEvent()
			}
			if (a.handler) {
				a.handler.call(a.scope || c, f, c, b, a)
			}
		}
	},
	afterRender : function() {
		if (this.floating && !this.hidden) {
			this.el.show()
		}
		if (this.title) {
			this.setTitle(this.title)
		}
		this.setAutoScroll();
		if (this.html) {
			this.body.update(Ext.isObject(this.html) ? Ext.DomHelper.markup(this.html) : this.html);
			delete this.html
		}
		if (this.contentEl) {
			var a = Ext.getDom(this.contentEl);
			Ext.fly(a).removeClass([ "x-hidden", "x-hide-display" ]);
			this.body.dom.appendChild(a)
		}
		if (this.collapsed) {
			this.collapsed = false;
			this.collapse(false)
		}
		Ext.Panel.superclass.afterRender.call(this);
		this.initEvents()
	},
	setAutoScroll : function() {
		if (this.rendered && this.autoScroll) {
			var a = this.body || this.el;
			if (a) {
				a.setOverflow("auto")
			}
		}
	},
	getKeyMap : function() {
		if (!this.keyMap) {
			this.keyMap = new Ext.KeyMap(this.el, this.keys)
		}
		return this.keyMap
	},
	initEvents : function() {
		if (this.keys) {
			this.getKeyMap()
		}
		if (this.draggable) {
			this.initDraggable()
		}
	},
	initDraggable : function() {
		this.dd = new Ext.Panel.DD(this, typeof this.draggable == "boolean" ? null : this.draggable)
	},
	beforeEffect : function() {
		if (this.floating) {
			this.el.beforeAction()
		}
		this.el.addClass("x-panel-animated")
	},
	afterEffect : function() {
		this.syncShadow();
		this.el.removeClass("x-panel-animated")
	},
	createEffect : function(c, b, d) {
		var e = {
			scope : d,
			block : true
		};
		if (c === true) {
			e.callback = b;
			return e
		} else {
			if (!c.callback) {
				e.callback = b
			} else {
				e.callback = function() {
					b.call(d);
					Ext.callback(c.callback, c.scope)
				}
			}
		}
		return Ext.applyIf(e, c)
	},
	collapse : function(b) {
		if (this.collapsed || this.el.hasFxBlock() || this.fireEvent("beforecollapse", this, b) === false) {
			return
		}
		var a = b === true || (b !== false && this.animCollapse);
		this.beforeEffect();
		this.onCollapse(a, b);
		return this
	},
	onCollapse : function(a, b) {
		if (a) {
			this[this.collapseEl].slideOut(this.slideAnchor, Ext.apply(this.createEffect(b || true, this.afterCollapse,
					this), this.collapseDefaults))
		} else {
			this[this.collapseEl].hide();
			this.afterCollapse()
		}
	},
	afterCollapse : function() {
		this.collapsed = true;
		this.el.addClass(this.collapsedCls);
		this.afterEffect();
		this.fireEvent("collapse", this)
	},
	expand : function(b) {
		if (!this.collapsed || this.el.hasFxBlock() || this.fireEvent("beforeexpand", this, b) === false) {
			return
		}
		var a = b === true || (b !== false && this.animCollapse);
		this.el.removeClass(this.collapsedCls);
		this.beforeEffect();
		this.onExpand(a, b);
		return this
	},
	onExpand : function(a, b) {
		if (a) {
			this[this.collapseEl].slideIn(this.slideAnchor, Ext.apply(this.createEffect(b || true, this.afterExpand,
					this), this.expandDefaults))
		} else {
			this[this.collapseEl].show();
			this.afterExpand()
		}
	},
	afterExpand : function() {
		this.collapsed = false;
		this.afterEffect();
		if (this.deferLayout !== undefined) {
			this.doLayout(true)
		}
		this.fireEvent("expand", this)
	},
	toggleCollapse : function(a) {
		this[this.collapsed ? "expand" : "collapse"](a);
		return this
	},
	onDisable : function() {
		if (this.rendered && this.maskDisabled) {
			this.el.mask()
		}
		Ext.Panel.superclass.onDisable.call(this)
	},
	onEnable : function() {
		if (this.rendered && this.maskDisabled) {
			this.el.unmask()
		}
		Ext.Panel.superclass.onEnable.call(this)
	},
	onResize : function(b, c) {
		if (b !== undefined || c !== undefined) {
			if (!this.collapsed) {
				if (typeof b == "number") {
					b = this.adjustBodyWidth(b - this.getFrameWidth());
					if (this.tbar) {
						this.tbar.setWidth(b);
						if (this.topToolbar) {
							this.topToolbar.setSize(b)
						}
					}
					if (this.bbar) {
						this.bbar.setWidth(b);
						if (this.bottomToolbar) {
							this.bottomToolbar.setSize(b)
						}
					}
					if (this.fbar) {
						var d = this.fbar, e = 1, a = Ext.isStrict;
						if (this.buttonAlign == "left") {
							e = b - d.container.getFrameWidth("lr")
						} else {
							if (Ext.isIE || Ext.isWebKit) {
								if (!(this.buttonAlign == "center" && Ext.isWebKit) && (!a || (!Ext.isIE8 && a))) {
									(function() {
										d.setWidth(d.getEl().child(".x-toolbar-ct").getWidth())
									}).defer(1)
								} else {
									e = "auto"
								}
							} else {
								e = "auto"
							}
						}
						d.setWidth(e)
					}
					this.body.setWidth(b)
				} else {
					if (b == "auto") {
						this.body.setWidth(b)
					}
				}
				if (typeof c == "number") {
					c = Math.max(0, this.adjustBodyHeight(c - this.getFrameHeight()));
					this.body.setHeight(c)
				} else {
					if (c == "auto") {
						this.body.setHeight(c)
					}
				}
				if (this.disabled && this.el._mask) {
					this.el._mask.setSize(this.el.dom.clientWidth, this.el.getHeight())
				}
			} else {
				this.queuedBodySize = {
					width : b,
					height : c
				};
				if (!this.queuedExpand && this.allowQueuedExpand !== false) {
					this.queuedExpand = true;
					this.on("expand", function() {
						delete this.queuedExpand;
						this.onResize(this.queuedBodySize.width, this.queuedBodySize.height);
						this.doLayout()
					}, this, {
						single : true
					})
				}
			}
			this.fireEvent("bodyresize", this, b, c)
		}
		this.syncShadow()
	},
	adjustBodyHeight : function(a) {
		return a
	},
	adjustBodyWidth : function(a) {
		return a
	},
	onPosition : function() {
		this.syncShadow()
	},
	getFrameWidth : function() {
		var b = this.el.getFrameWidth("lr") + this.bwrap.getFrameWidth("lr");
		if (this.frame) {
			var a = this.bwrap.dom.firstChild;
			b += (Ext.fly(a).getFrameWidth("l") + Ext.fly(a.firstChild).getFrameWidth("r"));
			var c = this.bwrap.dom.firstChild.firstChild.firstChild;
			b += Ext.fly(c).getFrameWidth("lr")
		}
		return b
	},
	getFrameHeight : function() {
		var a = this.el.getFrameWidth("tb") + this.bwrap.getFrameWidth("tb");
		a += (this.tbar ? this.tbar.getHeight() : 0) + (this.bbar ? this.bbar.getHeight() : 0);
		if (this.frame) {
			var c = this.el.dom.firstChild;
			var d = this.bwrap.dom.lastChild;
			a += (c.offsetHeight + d.offsetHeight);
			var b = this.bwrap.dom.firstChild.firstChild.firstChild;
			a += Ext.fly(b).getFrameWidth("tb")
		} else {
			a += (this.header ? this.header.getHeight() : 0) + (this.footer ? this.footer.getHeight() : 0)
		}
		return a
	},
	getInnerWidth : function() {
		return this.getSize().width - this.getFrameWidth()
	},
	getInnerHeight : function() {
		return this.getSize().height - this.getFrameHeight()
	},
	syncShadow : function() {
		if (this.floating) {
			this.el.sync(true)
		}
	},
	getLayoutTarget : function() {
		return this.body
	},
	setTitle : function(b, a) {
		this.title = b;
		if (this.header && this.headerAsText) {
			this.header.child("span").update(b)
		}
		if (a) {
			this.setIconClass(a)
		}
		this.fireEvent("titlechange", this, b);
		return this
	},
	getUpdater : function() {
		return this.body.getUpdater()
	},
	load : function() {
		var a = this.body.getUpdater();
		a.update.apply(a, arguments);
		return this
	},
	beforeDestroy : function() {
		if (this.header) {
			this.header.removeAllListeners();
			if (this.headerAsText) {
				Ext.Element.uncache(this.header.child("span"))
			}
		}
		Ext.Element.uncache(this.header, this.tbar, this.bbar, this.footer, this.body, this.bwrap);
		if (this.tools) {
			for ( var c in this.tools) {
				Ext.destroy(this.tools[c])
			}
		}
		if (this.buttons) {
			for ( var a in this.buttons) {
				Ext.destroy(this.buttons[a])
			}
		}
		Ext.destroy(this.toolbars);
		Ext.Panel.superclass.beforeDestroy.call(this)
	},
	createClasses : function() {
		this.headerCls = this.baseCls + "-header";
		this.headerTextCls = this.baseCls + "-header-text";
		this.bwrapCls = this.baseCls + "-bwrap";
		this.tbarCls = this.baseCls + "-tbar";
		this.bodyCls = this.baseCls + "-body";
		this.bbarCls = this.baseCls + "-bbar";
		this.footerCls = this.baseCls + "-footer"
	},
	createGhost : function(a, e, b) {
		var d = document.createElement("div");
		d.className = "x-panel-ghost " + (a ? a : "");
		if (this.header) {
			d.appendChild(this.el.dom.firstChild.cloneNode(true))
		}
		Ext.fly(d.appendChild(document.createElement("ul"))).setHeight(this.bwrap.getHeight());
		d.style.width = this.el.dom.offsetWidth + "px";
		if (!b) {
			this.container.dom.appendChild(d)
		} else {
			Ext.getDom(b).appendChild(d)
		}
		if (e !== false && this.el.useShim !== false) {
			var c = new Ext.Layer({
				shadow : false,
				useDisplay : true,
				constrain : false
			}, d);
			c.show();
			return c
		} else {
			return new Ext.Element(d)
		}
	},
	doAutoLoad : function() {
		var a = this.body.getUpdater();
		if (this.renderer) {
			a.setRenderer(this.renderer)
		}
		a.update(Ext.isObject(this.autoLoad) ? this.autoLoad : {
			url : this.autoLoad
		})
	},
	getTool : function(a) {
		return this.tools[a]
	}
});
Ext.reg("panel", Ext.Panel);
Ext.Editor = function(b, a) {
	if (b.field) {
		this.field = Ext.create(b.field, "textfield");
		a = Ext.apply({}, b);
		delete a.field
	} else {
		this.field = b
	}
	Ext.Editor.superclass.constructor.call(this, a)
};
Ext.extend(Ext.Editor, Ext.Component, {
	value : "",
	alignment : "c-c?",
	shadow : "frame",
	constrain : false,
	swallowKeys : true,
	completeOnEnter : false,
	cancelOnEsc : false,
	updateEl : false,
	initComponent : function() {
		Ext.Editor.superclass.initComponent.call(this);
		this.addEvents("beforestartedit", "startedit", "beforecomplete", "complete", "canceledit", "specialkey")
	},
	onRender : function(b, a) {
		this.el = new Ext.Layer({
			shadow : this.shadow,
			cls : "x-editor",
			parentEl : b,
			shim : this.shim,
			shadowOffset : this.shadowOffset || 4,
			id : this.id,
			constrain : this.constrain
		});
		if (this.zIndex) {
			this.el.setZIndex(this.zIndex)
		}
		this.el.setStyle("overflow", Ext.isGecko ? "auto" : "hidden");
		if (this.field.msgTarget != "title") {
			this.field.msgTarget = "qtip"
		}
		this.field.inEditor = true;
		this.field.render(this.el);
		if (Ext.isGecko) {
			this.field.el.dom.setAttribute("autocomplete", "off")
		}
		this.mon(this.field, "specialkey", this.onSpecialKey, this);
		if (this.swallowKeys) {
			this.field.el.swallowEvent([ "keydown", "keypress" ])
		}
		this.field.show();
		this.mon(this.field, "blur", this.onBlur, this);
		if (this.field.grow) {
			this.mon(this.field, "autosize", this.el.sync, this.el, {
				delay : 1
			})
		}
	},
	onSpecialKey : function(c, b) {
		var a = b.getKey();
		if (this.completeOnEnter && a == b.ENTER) {
			b.stopEvent();
			this.completeEdit()
		} else {
			if (this.cancelOnEsc && a == b.ESC) {
				this.cancelEdit()
			} else {
				this.fireEvent("specialkey", c, b)
			}
		}
		if (this.field.triggerBlur && (a == b.ENTER || a == b.ESC || a == b.TAB)) {
			this.field.triggerBlur()
		}
	},
	startEdit : function(b, c) {
		if (this.editing) {
			this.completeEdit()
		}
		this.boundEl = Ext.get(b);
		var a = c !== undefined ? c : this.boundEl.dom.innerHTML;
		if (!this.rendered) {
			this.render(this.parentEl || document.body)
		}
		if (this.fireEvent("beforestartedit", this, this.boundEl, a) === false) {
			return
		}
		this.startValue = a;
		this.field.setValue(a);
		this.doAutoSize();
		this.el.alignTo(this.boundEl, this.alignment);
		this.editing = true;
		this.show()
	},
	doAutoSize : function() {
		if (this.autoSize) {
			var a = this.boundEl.getSize();
			switch (this.autoSize) {
			case "width":
				this.setSize(a.width, "");
				break;
			case "height":
				this.setSize("", a.height);
				break;
			default:
				this.setSize(a.width, a.height)
			}
		}
	},
	setSize : function(a, b) {
		delete this.field.lastSize;
		this.field.setSize(a, b);
		if (this.el) {
			if (Ext.isGecko2 || Ext.isOpera) {
				this.el.setSize(a, b)
			}
			this.el.sync()
		}
	},
	realign : function() {
		this.el.alignTo(this.boundEl, this.alignment)
	},
	completeEdit : function(a) {
		if (!this.editing) {
			return
		}
		var b = this.getValue();
		if (!this.field.isValid()) {
			if (this.revertInvalid !== false) {
				this.cancelEdit(a)
			}
			return
		}
		if (String(b) === String(this.startValue) && this.ignoreNoChange) {
			this.hideEdit(a);
			return
		}
		if (this.fireEvent("beforecomplete", this, b, this.startValue) !== false) {
			b = this.getValue();
			if (this.updateEl && this.boundEl) {
				this.boundEl.update(b)
			}
			this.hideEdit(a);
			this.fireEvent("complete", this, b, this.startValue)
		}
	},
	onShow : function() {
		this.el.show();
		if (this.hideEl !== false) {
			this.boundEl.hide()
		}
		this.field.show();
		if (Ext.isIE && !this.fixIEFocus) {
			this.fixIEFocus = true;
			this.deferredFocus.defer(50, this)
		} else {
			this.field.focus()
		}
		this.fireEvent("startedit", this.boundEl, this.startValue)
	},
	deferredFocus : function() {
		if (this.editing) {
			this.field.focus()
		}
	},
	cancelEdit : function(a) {
		if (this.editing) {
			var b = this.getValue();
			this.setValue(this.startValue);
			this.hideEdit(a);
			this.fireEvent("canceledit", this, b, this.startValue)
		}
	},
	hideEdit : function(a) {
		if (a !== true) {
			this.editing = false;
			this.hide()
		}
	},
	onBlur : function() {
		if (this.allowBlur !== true && this.editing) {
			this.completeEdit()
		}
	},
	onHide : function() {
		if (this.editing) {
			this.completeEdit();
			return
		}
		this.field.blur();
		if (this.field.collapse) {
			this.field.collapse()
		}
		this.el.hide();
		if (this.hideEl !== false) {
			this.boundEl.show()
		}
	},
	setValue : function(a) {
		this.field.setValue(a)
	},
	getValue : function() {
		return this.field.getValue()
	},
	beforeDestroy : function() {
		Ext.destroy(this.field);
		this.field = null
	}
});
Ext.reg("editor", Ext.Editor);
Ext.ColorPalette = function(a) {
	Ext.ColorPalette.superclass.constructor.call(this, a);
	this.addEvents("select");
	if (this.handler) {
		this.on("select", this.handler, this.scope, true)
	}
};
Ext
		.extend(
				Ext.ColorPalette,
				Ext.Component,
				{
					itemCls : "x-color-palette",
					value : null,
					clickEvent : "click",
					ctype : "Ext.ColorPalette",
					allowReselect : false,
					colors : [ "000000", "993300", "333300", "003300", "003366", "000080", "333399", "333333",
							"800000", "FF6600", "808000", "008000", "008080", "0000FF", "666699", "808080", "FF0000",
							"FF9900", "99CC00", "339966", "33CCCC", "3366FF", "800080", "969696", "FF00FF", "FFCC00",
							"FFFF00", "00FF00", "00FFFF", "00CCFF", "993366", "C0C0C0", "FF99CC", "FFCC99", "FFFF99",
							"CCFFCC", "CCFFFF", "99CCFF", "CC99FF", "FFFFFF" ],
					onRender : function(b, a) {
						var c = this.tpl
								|| new Ext.XTemplate(
										'<tpl for="."><a href="#" class="color-{.}" hidefocus="on"><em><span style="background:#{.}" unselectable="on">&#160;</span></em></a></tpl>');
						var d = document.createElement("div");
						d.id = this.getId();
						d.className = this.itemCls;
						c.overwrite(d, this.colors);
						b.dom.insertBefore(d, a);
						this.el = Ext.get(d);
						this.mon(this.el, this.clickEvent, this.handleClick, this, {
							delegate : "a"
						});
						if (this.clickEvent != "click") {
							this.mon(this.el, "click", Ext.emptyFn, this, {
								delegate : "a",
								preventDefault : true
							})
						}
					},
					afterRender : function() {
						Ext.ColorPalette.superclass.afterRender.call(this);
						if (this.value) {
							var a = this.value;
							this.value = null;
							this.select(a)
						}
					},
					handleClick : function(b, a) {
						b.preventDefault();
						if (!this.disabled) {
							var d = a.className.match(/(?:^|\s)color-(.{6})(?:\s|$)/)[1];
							this.select(d.toUpperCase())
						}
					},
					select : function(a) {
						a = a.replace("#", "");
						if (a != this.value || this.allowReselect) {
							var b = this.el;
							if (this.value) {
								b.child("a.color-" + this.value).removeClass("x-color-palette-sel")
							}
							b.child("a.color-" + a).addClass("x-color-palette-sel");
							this.value = a;
							this.fireEvent("select", this, a)
						}
					}
				});
Ext.reg("colorpalette", Ext.ColorPalette);
Ext.DatePicker = Ext
		.extend(
				Ext.BoxComponent,
				{
					todayText : "Today",
					okText : "&#160;OK&#160;",
					cancelText : "Cancel",
					todayTip : "{0} (Spacebar)",
					minText : "This date is before the minimum date",
					maxText : "This date is after the maximum date",
					format : "m/d/y",
					disabledDaysText : "Disabled",
					disabledDatesText : "Disabled",
					monthNames : Date.monthNames,
					dayNames : Date.dayNames,
					nextText : "Next Month (Control+Right)",
					prevText : "Previous Month (Control+Left)",
					monthYearText : "Choose a month (Control+Up/Down to move years)",
					startDay : 0,
					showToday : true,
					initComponent : function() {
						Ext.DatePicker.superclass.initComponent.call(this);
						this.value = this.value ? this.value.clearTime() : new Date().clearTime();
						this.addEvents("select");
						if (this.handler) {
							this.on("select", this.handler, this.scope || this)
						}
						this.initDisabledDays()
					},
					initDisabledDays : function() {
						if (!this.disabledDatesRE && this.disabledDates) {
							var b = this.disabledDates, a = b.length - 1, c = "(?:";
							Ext.each(b, function(f, e) {
								c += Ext.isDate(f) ? "^" + Ext.escapeRe(f.dateFormat(this.format)) + "$" : b[e];
								if (e != a) {
									c += "|"
								}
							}, this);
							this.disabledDatesRE = new RegExp(c + ")")
						}
					},
					setDisabledDates : function(a) {
						if (Ext.isArray(a)) {
							this.disabledDates = a;
							this.disabledDatesRE = null
						} else {
							this.disabledDatesRE = a
						}
						this.initDisabledDays();
						this.update(this.value, true)
					},
					setDisabledDays : function(a) {
						this.disabledDays = a;
						this.update(this.value, true)
					},
					setMinDate : function(a) {
						this.minDate = a;
						this.update(this.value, true)
					},
					setMaxDate : function(a) {
						this.maxDate = a;
						this.update(this.value, true)
					},
					setValue : function(b) {
						var a = this.value;
						this.value = b.clearTime(true);
						if (this.el) {
							this.update(this.value)
						}
					},
					getValue : function() {
						return this.value
					},
					focus : function() {
						if (this.el) {
							this.update(this.activeDate)
						}
					},
					onEnable : function(a) {
						Ext.DatePicker.superclass.onEnable.call(this);
						this.doDisabled(false);
						this.update(a ? this.value : this.activeDate);
						if (Ext.isIE) {
							this.el.repaint()
						}
					},
					onDisable : function() {
						Ext.DatePicker.superclass.onDisable.call(this);
						this.doDisabled(true);
						if (Ext.isIE && !Ext.isIE8) {
							Ext.each([].concat(this.textNodes, this.el.query("th span")), function(a) {
								Ext.fly(a).repaint()
							})
						}
					},
					doDisabled : function(a) {
						this.keyNav.setDisabled(a);
						this.prevRepeater.setDisabled(a);
						this.nextRepeater.setDisabled(a);
						if (this.showToday) {
							this.todayKeyListener.setDisabled(a);
							this.todayBtn.setDisabled(a)
						}
					},
					onRender : function(e, b) {
						var a = [
								'<table cellspacing="0">',
								'<tr><td class="x-date-left"><a href="#" title="',
								this.prevText,
								'">&#160;</a></td><td class="x-date-middle" align="center"></td><td class="x-date-right"><a href="#" title="',
								this.nextText, '">&#160;</a></td></tr>',
								'<tr><td colspan="3"><table class="x-date-inner" cellspacing="0"><thead><tr>' ], c = this.dayNames, g;
						for (g = 0; g < 7; g++) {
							var j = this.startDay + g;
							if (j > 6) {
								j = j - 7
							}
							a.push("<th><span>", c[j].substr(0, 1), "</span></th>")
						}
						a[a.length] = "</tr></thead><tbody><tr>";
						for (g = 0; g < 42; g++) {
							if (g % 7 === 0 && g !== 0) {
								a[a.length] = "</tr><tr>"
							}
							a[a.length] = '<td><a href="#" hidefocus="on" class="x-date-date" tabIndex="1"><em><span></span></em></a></td>'
						}
						a.push("</tr></tbody></table></td></tr>",
								this.showToday ? '<tr><td colspan="3" class="x-date-bottom" align="center"></td></tr>'
										: "", '</table><div class="x-date-mp"></div>');
						var h = document.createElement("div");
						h.className = "x-date-picker";
						h.innerHTML = a.join("");
						e.dom.insertBefore(h, b);
						this.el = Ext.get(h);
						this.eventEl = Ext.get(h.firstChild);
						this.prevRepeater = new Ext.util.ClickRepeater(this.el.child("td.x-date-left a"), {
							handler : this.showPrevMonth,
							scope : this,
							preventDefault : true,
							stopDefault : true
						});
						this.nextRepeater = new Ext.util.ClickRepeater(this.el.child("td.x-date-right a"), {
							handler : this.showNextMonth,
							scope : this,
							preventDefault : true,
							stopDefault : true
						});
						this.monthPicker = this.el.down("div.x-date-mp");
						this.monthPicker.enableDisplayMode("block");
						this.keyNav = new Ext.KeyNav(this.eventEl, {
							left : function(d) {
								if (d.ctrlKey) {
									this.showPrevMonth()
								} else {
									this.update(this.activeDate.add("d", -1))
								}
							},
							right : function(d) {
								if (d.ctrlKey) {
									this.showNextMonth()
								} else {
									this.update(this.activeDate.add("d", 1))
								}
							},
							up : function(d) {
								if (d.ctrlKey) {
									this.showNextYear()
								} else {
									this.update(this.activeDate.add("d", -7))
								}
							},
							down : function(d) {
								if (d.ctrlKey) {
									this.showPrevYear()
								} else {
									this.update(this.activeDate.add("d", 7))
								}
							},
							pageUp : function(d) {
								this.showNextMonth()
							},
							pageDown : function(d) {
								this.showPrevMonth()
							},
							enter : function(d) {
								d.stopPropagation();
								return true
							},
							scope : this
						});
						this.el.unselectable();
						this.cells = this.el.select("table.x-date-inner tbody td");
						this.textNodes = this.el.query("table.x-date-inner tbody span");
						this.mbtn = new Ext.Button({
							text : "&#160;",
							tooltip : this.monthYearText,
							renderTo : this.el.child("td.x-date-middle", true)
						});
						this.mbtn.el.child("em").addClass("x-btn-arrow");
						if (this.showToday) {
							this.todayKeyListener = this.eventEl.addKeyListener(Ext.EventObject.SPACE,
									this.selectToday, this);
							var f = (new Date()).dateFormat(this.format);
							this.todayBtn = new Ext.Button({
								renderTo : this.el.child("td.x-date-bottom", true),
								text : String.format(this.todayText, f),
								tooltip : String.format(this.todayTip, f),
								handler : this.selectToday,
								scope : this
							})
						}
						this.mon(this.eventEl, "mousewheel", this.handleMouseWheel, this);
						this.mon(this.eventEl, "click", this.handleDateClick, this, {
							delegate : "a.x-date-date"
						});
						this.mon(this.mbtn, "click", this.showMonthPicker, this);
						this.onEnable(true)
					},
					createMonthPicker : function() {
						if (!this.monthPicker.dom.firstChild) {
							var a = [ '<table border="0" cellspacing="0">' ];
							for ( var b = 0; b < 6; b++) {
								a
										.push(
												'<tr><td class="x-date-mp-month"><a href="#">',
												Date.getShortMonthName(b),
												"</a></td>",
												'<td class="x-date-mp-month x-date-mp-sep"><a href="#">',
												Date.getShortMonthName(b + 6),
												"</a></td>",
												b === 0 ? '<td class="x-date-mp-ybtn" align="center"><a class="x-date-mp-prev"></a></td><td class="x-date-mp-ybtn" align="center"><a class="x-date-mp-next"></a></td></tr>'
														: '<td class="x-date-mp-year"><a href="#"></a></td><td class="x-date-mp-year"><a href="#"></a></td></tr>')
							}
							a
									.push(
											'<tr class="x-date-mp-btns"><td colspan="4"><button type="button" class="x-date-mp-ok">',
											this.okText, '</button><button type="button" class="x-date-mp-cancel">',
											this.cancelText, "</button></td></tr>", "</table>");
							this.monthPicker.update(a.join(""));
							this.mon(this.monthPicker, "click", this.onMonthClick, this);
							this.mon(this.monthPicker, "dblclick", this.onMonthDblClick, this);
							this.mpMonths = this.monthPicker.select("td.x-date-mp-month");
							this.mpYears = this.monthPicker.select("td.x-date-mp-year");
							this.mpMonths.each(function(c, d, e) {
								e += 1;
								if ((e % 2) === 0) {
									c.dom.xmonth = 5 + Math.round(e * 0.5)
								} else {
									c.dom.xmonth = Math.round((e - 1) * 0.5)
								}
							})
						}
					},
					showMonthPicker : function() {
						if (!this.disabled) {
							this.createMonthPicker();
							var a = this.el.getSize();
							this.monthPicker.setSize(a);
							this.monthPicker.child("table").setSize(a);
							this.mpSelMonth = (this.activeDate || this.value).getMonth();
							this.updateMPMonth(this.mpSelMonth);
							this.mpSelYear = (this.activeDate || this.value).getFullYear();
							this.updateMPYear(this.mpSelYear);
							this.monthPicker.slideIn("t", {
								duration : 0.2
							})
						}
					},
					updateMPYear : function(e) {
						this.mpyear = e;
						var c = this.mpYears.elements;
						for ( var b = 1; b <= 10; b++) {
							var d = c[b - 1], a;
							if ((b % 2) === 0) {
								a = e + Math.round(b * 0.5);
								d.firstChild.innerHTML = a;
								d.xyear = a
							} else {
								a = e - (5 - Math.round(b * 0.5));
								d.firstChild.innerHTML = a;
								d.xyear = a
							}
							this.mpYears.item(b - 1)[a == this.mpSelYear ? "addClass" : "removeClass"]("x-date-mp-sel")
						}
					},
					updateMPMonth : function(a) {
						this.mpMonths.each(function(b, c, d) {
							b[b.dom.xmonth == a ? "addClass" : "removeClass"]("x-date-mp-sel")
						})
					},
					selectMPMonth : function(a) {
					},
					onMonthClick : function(f, b) {
						f.stopEvent();
						var c = new Ext.Element(b), a;
						if (c.is("button.x-date-mp-cancel")) {
							this.hideMonthPicker()
						} else {
							if (c.is("button.x-date-mp-ok")) {
								var g = new Date(this.mpSelYear, this.mpSelMonth, (this.activeDate || this.value)
										.getDate());
								if (g.getMonth() != this.mpSelMonth) {
									g = new Date(this.mpSelYear, this.mpSelMonth, 1).getLastDateOfMonth()
								}
								this.update(g);
								this.hideMonthPicker()
							} else {
								if ((a = c.up("td.x-date-mp-month", 2))) {
									this.mpMonths.removeClass("x-date-mp-sel");
									a.addClass("x-date-mp-sel");
									this.mpSelMonth = a.dom.xmonth
								} else {
									if ((a = c.up("td.x-date-mp-year", 2))) {
										this.mpYears.removeClass("x-date-mp-sel");
										a.addClass("x-date-mp-sel");
										this.mpSelYear = a.dom.xyear
									} else {
										if (c.is("a.x-date-mp-prev")) {
											this.updateMPYear(this.mpyear - 10)
										} else {
											if (c.is("a.x-date-mp-next")) {
												this.updateMPYear(this.mpyear + 10)
											}
										}
									}
								}
							}
						}
					},
					onMonthDblClick : function(d, b) {
						d.stopEvent();
						var c = new Ext.Element(b), a;
						if ((a = c.up("td.x-date-mp-month", 2))) {
							this.update(new Date(this.mpSelYear, a.dom.xmonth, (this.activeDate || this.value)
									.getDate()));
							this.hideMonthPicker()
						} else {
							if ((a = c.up("td.x-date-mp-year", 2))) {
								this.update(new Date(a.dom.xyear, this.mpSelMonth, (this.activeDate || this.value)
										.getDate()));
								this.hideMonthPicker()
							}
						}
					},
					hideMonthPicker : function(a) {
						if (this.monthPicker) {
							if (a === true) {
								this.monthPicker.hide()
							} else {
								this.monthPicker.slideOut("t", {
									duration : 0.2
								})
							}
						}
					},
					showPrevMonth : function(a) {
						this.update(this.activeDate.add("mo", -1))
					},
					showNextMonth : function(a) {
						this.update(this.activeDate.add("mo", 1))
					},
					showPrevYear : function() {
						this.update(this.activeDate.add("y", -1))
					},
					showNextYear : function() {
						this.update(this.activeDate.add("y", 1))
					},
					handleMouseWheel : function(a) {
						a.stopEvent();
						if (!this.disabled) {
							var b = a.getWheelDelta();
							if (b > 0) {
								this.showPrevMonth()
							} else {
								if (b < 0) {
									this.showNextMonth()
								}
							}
						}
					},
					handleDateClick : function(b, a) {
						b.stopEvent();
						if (!this.disabled && a.dateValue && !Ext.fly(a.parentNode).hasClass("x-date-disabled")) {
							this.setValue(new Date(a.dateValue));
							this.fireEvent("select", this, this.value)
						}
					},
					selectToday : function() {
						if (this.todayBtn && !this.todayBtn.disabled) {
							this.setValue(new Date().clearTime());
							this.fireEvent("select", this, this.value)
						}
					},
					update : function(G, A) {
						var a = this.activeDate, o = this.isVisible();
						this.activeDate = G;
						if (!A && a && this.el) {
							var n = G.getTime();
							if (a.getMonth() == G.getMonth() && a.getFullYear() == G.getFullYear()) {
								this.cells.removeClass("x-date-selected");
								this.cells.each(function(d) {
									if (d.dom.firstChild.dateValue == n) {
										d.addClass("x-date-selected");
										if (o) {
											Ext.fly(d.dom.firstChild).focus(50)
										}
										return false
									}
								});
								return
							}
						}
						var j = G.getDaysInMonth();
						var p = G.getFirstDateOfMonth();
						var f = p.getDay() - this.startDay;
						if (f <= this.startDay) {
							f += 7
						}
						var B = G.add("mo", -1);
						var g = B.getDaysInMonth() - f;
						var e = this.cells.elements;
						var q = this.textNodes;
						j += f;
						var y = 86400000;
						var D = (new Date(B.getFullYear(), B.getMonth(), g)).clearTime();
						var C = new Date().clearTime().getTime();
						var u = G.clearTime().getTime();
						var s = this.minDate ? this.minDate.clearTime() : Number.NEGATIVE_INFINITY;
						var x = this.maxDate ? this.maxDate.clearTime() : Number.POSITIVE_INFINITY;
						var F = this.disabledDatesRE;
						var r = this.disabledDatesText;
						var I = this.disabledDays ? this.disabledDays.join("") : false;
						var E = this.disabledDaysText;
						var z = this.format;
						if (this.showToday) {
							var l = new Date().clearTime();
							var c = (l < s || l > x || (F && z && F.test(l.dateFormat(z))) || (I && I.indexOf(l
									.getDay()) != -1));
							if (!this.disabled) {
								this.todayBtn.setDisabled(c);
								this.todayKeyListener[c ? "disable" : "enable"]()
							}
						}
						var k = function(J, d) {
							d.title = "";
							var i = D.getTime();
							d.firstChild.dateValue = i;
							if (i == C) {
								d.className += " x-date-today";
								d.title = J.todayText
							}
							if (i == u) {
								d.className += " x-date-selected";
								if (o) {
									Ext.fly(d.firstChild).focus(50)
								}
							}
							if (i < s) {
								d.className = " x-date-disabled";
								d.title = J.minText;
								return
							}
							if (i > x) {
								d.className = " x-date-disabled";
								d.title = J.maxText;
								return
							}
							if (I) {
								if (I.indexOf(D.getDay()) != -1) {
									d.title = E;
									d.className = " x-date-disabled"
								}
							}
							if (F && z) {
								var w = D.dateFormat(z);
								if (F.test(w)) {
									d.title = r.replace("%0", w);
									d.className = " x-date-disabled"
								}
							}
						};
						var v = 0;
						for (; v < f; v++) {
							q[v].innerHTML = (++g);
							D.setDate(D.getDate() + 1);
							e[v].className = "x-date-prevday";
							k(this, e[v])
						}
						for (; v < j; v++) {
							var b = v - f + 1;
							q[v].innerHTML = (b);
							D.setDate(D.getDate() + 1);
							e[v].className = "x-date-active";
							k(this, e[v])
						}
						var H = 0;
						for (; v < 42; v++) {
							q[v].innerHTML = (++H);
							D.setDate(D.getDate() + 1);
							e[v].className = "x-date-nextday";
							k(this, e[v])
						}
						this.mbtn.setText(this.monthNames[G.getMonth()] + " " + G.getFullYear());
						if (!this.internalRender) {
							var h = this.el.dom.firstChild;
							var m = h.offsetWidth;
							this.el.setWidth(m + this.el.getBorderWidth("lr"));
							Ext.fly(h).setWidth(m);
							this.internalRender = true;
							if (Ext.isOpera && !this.secondPass) {
								h.rows[0].cells[1].style.width = (m - (h.rows[0].cells[0].offsetWidth + h.rows[0].cells[2].offsetWidth))
										+ "px";
								this.secondPass = true;
								this.update.defer(10, this, [ G ])
							}
						}
					},
					beforeDestroy : function() {
						if (this.rendered) {
							this.keyNav.disable();
							this.keyNav = null;
							Ext.destroy(this.leftClickRpt, this.rightClickRpt, this.monthPicker, this.eventEl,
									this.mbtn, this.todayBtn)
						}
					}
				});
Ext.reg("datepicker", Ext.DatePicker);
Ext.LoadMask = function(c, b) {
	this.el = Ext.get(c);
	Ext.apply(this, b);
	if (this.store) {
		this.store.on("beforeload", this.onBeforeLoad, this);
		this.store.on("load", this.onLoad, this);
		this.store.on("exception", this.onLoad, this);
		this.removeMask = Ext.value(this.removeMask, false)
	} else {
		var a = this.el.getUpdater();
		a.showLoadIndicator = false;
		a.on("beforeupdate", this.onBeforeLoad, this);
		a.on("update", this.onLoad, this);
		a.on("failure", this.onLoad, this);
		this.removeMask = Ext.value(this.removeMask, true)
	}
};
Ext.LoadMask.prototype = {
	msg : "Loading...",
	msgCls : "x-mask-loading",
	disabled : false,
	disable : function() {
		this.disabled = true
	},
	enable : function() {
		this.disabled = false
	},
	onLoad : function() {
		this.el.unmask(this.removeMask)
	},
	onBeforeLoad : function() {
		if (!this.disabled) {
			this.el.mask(this.msg, this.msgCls)
		}
	},
	show : function() {
		this.onBeforeLoad()
	},
	hide : function() {
		this.onLoad()
	},
	destroy : function() {
		if (this.store) {
			this.store.un("beforeload", this.onBeforeLoad, this);
			this.store.un("load", this.onLoad, this);
			this.store.un("exception", this.onLoad, this)
		} else {
			var a = this.el.getUpdater();
			a.un("beforeupdate", this.onBeforeLoad, this);
			a.un("update", this.onLoad, this);
			a.un("failure", this.onLoad, this)
		}
	}
};
Ext.Slider = Ext.extend(Ext.BoxComponent, {
	vertical : false,
	minValue : 0,
	maxValue : 100,
	decimalPrecision : 0,
	keyIncrement : 1,
	increment : 0,
	clickRange : [ 5, 15 ],
	clickToChange : true,
	animate : true,
	dragging : false,
	initComponent : function() {
		if (!Ext.isDefined(this.value)) {
			this.value = this.minValue
		}
		Ext.Slider.superclass.initComponent.call(this);
		this.keyIncrement = Math.max(this.increment, this.keyIncrement);
		this.addEvents("beforechange", "change", "changecomplete", "dragstart", "drag", "dragend");
		if (this.vertical) {
			Ext.apply(this, Ext.Slider.Vertical)
		}
	},
	onRender : function() {
		this.autoEl = {
			cls : "x-slider " + (this.vertical ? "x-slider-vert" : "x-slider-horz"),
			cn : {
				cls : "x-slider-end",
				cn : {
					cls : "x-slider-inner",
					cn : [ {
						cls : "x-slider-thumb"
					}, {
						tag : "a",
						cls : "x-slider-focus",
						href : "#",
						tabIndex : "-1",
						hidefocus : "on"
					} ]
				}
			}
		};
		Ext.Slider.superclass.onRender.apply(this, arguments);
		this.endEl = this.el.first();
		this.innerEl = this.endEl.first();
		this.thumb = this.innerEl.first();
		this.halfThumb = (this.vertical ? this.thumb.getHeight() : this.thumb.getWidth()) / 2;
		this.focusEl = this.thumb.next();
		this.initEvents()
	},
	initEvents : function() {
		this.thumb.addClassOnOver("x-slider-thumb-over");
		this.mon(this.el, {
			scope : this,
			mousedown : this.onMouseDown,
			keydown : this.onKeyDown
		});
		this.focusEl.swallowEvent("click", true);
		this.tracker = new Ext.dd.DragTracker({
			onBeforeStart : this.onBeforeDragStart.createDelegate(this),
			onStart : this.onDragStart.createDelegate(this),
			onDrag : this.onDrag.createDelegate(this),
			onEnd : this.onDragEnd.createDelegate(this),
			tolerance : 3,
			autoStart : 300
		});
		this.tracker.initEl(this.thumb);
		this.on("beforedestroy", this.tracker.destroy, this.tracker)
	},
	onMouseDown : function(b) {
		if (this.disabled) {
			return
		}
		if (this.clickToChange && b.target != this.thumb.dom) {
			var a = this.innerEl.translatePoints(b.getXY());
			this.onClickChange(a)
		}
		this.focus()
	},
	onClickChange : function(a) {
		if (a.top > this.clickRange[0] && a.top < this.clickRange[1]) {
			this.setValue(Ext.util.Format.round(this.reverseValue(a.left), this.decimalPrecision), undefined, true)
		}
	},
	onKeyDown : function(b) {
		if (this.disabled) {
			b.preventDefault();
			return
		}
		var a = b.getKey();
		switch (a) {
		case b.UP:
		case b.RIGHT:
			b.stopEvent();
			if (b.ctrlKey) {
				this.setValue(this.maxValue, undefined, true)
			} else {
				this.setValue(this.value + this.keyIncrement, undefined, true)
			}
			break;
		case b.DOWN:
		case b.LEFT:
			b.stopEvent();
			if (b.ctrlKey) {
				this.setValue(this.minValue, undefined, true)
			} else {
				this.setValue(this.value - this.keyIncrement, undefined, true)
			}
			break;
		default:
			b.preventDefault()
		}
	},
	doSnap : function(b) {
		if (!this.increment || this.increment == 1 || !b) {
			return b
		}
		var d = b, c = this.increment;
		var a = b % c;
		if (a != 0) {
			d -= a;
			if (a * 2 > c) {
				d += c
			} else {
				if (a * 2 < -c) {
					d -= c
				}
			}
		}
		return d.constrain(this.minValue, this.maxValue)
	},
	afterRender : function() {
		Ext.Slider.superclass.afterRender.apply(this, arguments);
		if (this.value !== undefined) {
			var a = this.normalizeValue(this.value);
			if (a !== this.value) {
				delete this.value;
				this.setValue(a, false)
			} else {
				this.moveThumb(this.translateValue(a), false)
			}
		}
	},
	getRatio : function() {
		var a = this.innerEl.getWidth();
		var b = this.maxValue - this.minValue;
		return b == 0 ? a : (a / b)
	},
	normalizeValue : function(a) {
		a = this.doSnap(a);
		a = Ext.util.Format.round(a, this.decimalPrecision);
		a = a.constrain(this.minValue, this.maxValue);
		return a
	},
	setValue : function(b, a, c) {
		b = this.normalizeValue(b);
		if (b !== this.value && this.fireEvent("beforechange", this, b, this.value) !== false) {
			this.value = b;
			this.moveThumb(this.translateValue(b), a !== false);
			this.fireEvent("change", this, b);
			if (c) {
				this.fireEvent("changecomplete", this, b)
			}
		}
	},
	translateValue : function(a) {
		var b = this.getRatio();
		return (a * b) - (this.minValue * b) - this.halfThumb
	},
	reverseValue : function(b) {
		var a = this.getRatio();
		return (b + this.halfThumb + (this.minValue * a)) / a
	},
	moveThumb : function(b, a) {
		if (!a || this.animate === false) {
			this.thumb.setLeft(b)
		} else {
			this.thumb.shift({
				left : b,
				stopFx : true,
				duration : 0.35
			})
		}
	},
	focus : function() {
		this.focusEl.focus(10)
	},
	onBeforeDragStart : function(a) {
		return !this.disabled
	},
	onDragStart : function(a) {
		this.thumb.addClass("x-slider-thumb-drag");
		this.dragging = true;
		this.dragStartValue = this.value;
		this.fireEvent("dragstart", this, a)
	},
	onDrag : function(a) {
		var b = this.innerEl.translatePoints(this.tracker.getXY());
		this.setValue(Ext.util.Format.round(this.reverseValue(b.left), this.decimalPrecision), false);
		this.fireEvent("drag", this, a)
	},
	onDragEnd : function(a) {
		this.thumb.removeClass("x-slider-thumb-drag");
		this.dragging = false;
		this.fireEvent("dragend", this, a);
		if (this.dragStartValue != this.value) {
			this.fireEvent("changecomplete", this, this.value)
		}
	},
	onResize : function(a, b) {
		this.innerEl.setWidth(a - (this.el.getPadding("l") + this.endEl.getPadding("r")));
		this.syncThumb()
	},
	onDisable : function() {
		Ext.Slider.superclass.onDisable.call(this);
		this.thumb.addClass(this.disabledClass);
		if (Ext.isIE) {
			var a = this.thumb.getXY();
			this.thumb.hide();
			this.innerEl.addClass(this.disabledClass).dom.disabled = true;
			if (!this.thumbHolder) {
				this.thumbHolder = this.endEl.createChild({
					cls : "x-slider-thumb " + this.disabledClass
				})
			}
			this.thumbHolder.show().setXY(a)
		}
	},
	onEnable : function() {
		Ext.Slider.superclass.onEnable.call(this);
		this.thumb.removeClass(this.disabledClass);
		if (Ext.isIE) {
			this.innerEl.removeClass(this.disabledClass).dom.disabled = false;
			if (this.thumbHolder) {
				this.thumbHolder.hide()
			}
			this.thumb.show();
			this.syncThumb()
		}
	},
	syncThumb : function() {
		if (this.rendered) {
			this.moveThumb(this.translateValue(this.value))
		}
	},
	getValue : function() {
		return this.value
	}
});
Ext.reg("slider", Ext.Slider);
Ext.Slider.Vertical = {
	onResize : function(a, b) {
		this.innerEl.setHeight(b - (this.el.getPadding("t") + this.endEl.getPadding("b")));
		this.syncThumb()
	},
	getRatio : function() {
		var b = this.innerEl.getHeight();
		var a = this.maxValue - this.minValue;
		return b / a
	},
	moveThumb : function(b, a) {
		if (!a || this.animate === false) {
			this.thumb.setBottom(b)
		} else {
			this.thumb.shift({
				bottom : b,
				stopFx : true,
				duration : 0.35
			})
		}
	},
	onDrag : function(b) {
		var c = this.innerEl.translatePoints(this.tracker.getXY());
		var a = this.innerEl.getHeight() - c.top;
		this.setValue(this.minValue + Ext.util.Format.round(a / this.getRatio(), this.decimalPrecision), false);
		this.fireEvent("drag", this, b)
	},
	onClickChange : function(b) {
		if (b.left > this.clickRange[0] && b.left < this.clickRange[1]) {
			var a = this.innerEl.getHeight() - b.top;
			this.setValue(this.minValue + Ext.util.Format.round(a / this.getRatio(), this.decimalPrecision), undefined,
					true)
		}
	}
};
Ext.ProgressBar = Ext.extend(Ext.BoxComponent, {
	baseCls : "x-progress",
	animate : false,
	waitTimer : null,
	initComponent : function() {
		Ext.ProgressBar.superclass.initComponent.call(this);
		this.addEvents("update")
	},
	onRender : function(d, a) {
		var c = new Ext.Template('<div class="{cls}-wrap">', '<div class="{cls}-inner">', '<div class="{cls}-bar">',
				'<div class="{cls}-text">', "<div>&#160;</div>", "</div>", "</div>",
				'<div class="{cls}-text {cls}-text-back">', "<div>&#160;</div>", "</div>", "</div>", "</div>");
		this.el = a ? c.insertBefore(a, {
			cls : this.baseCls
		}, true) : c.append(d, {
			cls : this.baseCls
		}, true);
		if (this.id) {
			this.el.dom.id = this.id
		}
		var b = this.el.dom.firstChild;
		this.progressBar = Ext.get(b.firstChild);
		if (this.textEl) {
			this.textEl = Ext.get(this.textEl);
			delete this.textTopEl
		} else {
			this.textTopEl = Ext.get(this.progressBar.dom.firstChild);
			var e = Ext.get(b.childNodes[1]);
			this.textTopEl.setStyle("z-index", 99).addClass("x-hidden");
			this.textEl = new Ext.CompositeElement([ this.textTopEl.dom.firstChild, e.dom.firstChild ]);
			this.textEl.setWidth(b.offsetWidth)
		}
		this.progressBar.setHeight(b.offsetHeight)
	},
	afterRender : function() {
		Ext.ProgressBar.superclass.afterRender.call(this);
		if (this.value) {
			this.updateProgress(this.value, this.text)
		} else {
			this.updateText(this.text)
		}
	},
	updateProgress : function(c, d, b) {
		this.value = c || 0;
		if (d) {
			this.updateText(d)
		}
		if (this.rendered) {
			var a = Math.floor(c * this.el.dom.firstChild.offsetWidth);
			this.progressBar.setWidth(a, b === true || (b !== false && this.animate));
			if (this.textTopEl) {
				this.textTopEl.removeClass("x-hidden").setWidth(a)
			}
		}
		this.fireEvent("update", this, c, d);
		return this
	},
	wait : function(b) {
		if (!this.waitTimer) {
			var a = this;
			b = b || {};
			this.updateText(b.text);
			this.waitTimer = Ext.TaskMgr.start({
				run : function(c) {
					var d = b.increment || 10;
					this.updateProgress(((((c + d) % d) + 1) * (100 / d)) * 0.01, null, b.animate)
				},
				interval : b.interval || 1000,
				duration : b.duration,
				onStop : function() {
					if (b.fn) {
						b.fn.apply(b.scope || this)
					}
					this.reset()
				},
				scope : a
			})
		}
		return this
	},
	isWaiting : function() {
		return this.waitTimer !== null
	},
	updateText : function(a) {
		this.text = a || "&#160;";
		if (this.rendered) {
			this.textEl.update(this.text)
		}
		return this
	},
	syncProgressBar : function() {
		if (this.value) {
			this.updateProgress(this.value, this.text)
		}
		return this
	},
	setSize : function(a, c) {
		Ext.ProgressBar.superclass.setSize.call(this, a, c);
		if (this.textTopEl) {
			var b = this.el.dom.firstChild;
			this.textEl.setSize(b.offsetWidth, b.offsetHeight)
		}
		this.syncProgressBar();
		return this
	},
	reset : function(a) {
		this.updateProgress(0);
		if (this.textTopEl) {
			this.textTopEl.addClass("x-hidden")
		}
		if (this.waitTimer) {
			this.waitTimer.onStop = null;
			Ext.TaskMgr.stop(this.waitTimer);
			this.waitTimer = null
		}
		if (a === true) {
			this.hide()
		}
		return this
	}
});
Ext.reg("progress", Ext.ProgressBar);

(function() {
	var a = Ext.EventManager;
	var b = Ext.lib.Dom;
	Ext.dd.DragDrop = function(e, c, d) {
		if (e) {
			this.init(e, c, d)
		}
	};
	Ext.dd.DragDrop.prototype = {
		id : null,
		config : null,
		dragElId : null,
		handleElId : null,
		invalidHandleTypes : null,
		invalidHandleIds : null,
		invalidHandleClasses : null,
		startPageX : 0,
		startPageY : 0,
		groups : null,
		locked : false,
		lock : function() {
			this.locked = true
		},
		moveOnly : false,
		unlock : function() {
			this.locked = false
		},
		isTarget : true,
		padding : null,
		_domRef : null,
		__ygDragDrop : true,
		constrainX : false,
		constrainY : false,
		minX : 0,
		maxX : 0,
		minY : 0,
		maxY : 0,
		maintainOffset : false,
		xTicks : null,
		yTicks : null,
		primaryButtonOnly : true,
		available : false,
		hasOuterHandles : false,
		b4StartDrag : function(c, d) {
		},
		startDrag : function(c, d) {
		},
		b4Drag : function(c) {
		},
		onDrag : function(c) {
		},
		onDragEnter : function(c, d) {
		},
		b4DragOver : function(c) {
		},
		onDragOver : function(c, d) {
		},
		b4DragOut : function(c) {
		},
		onDragOut : function(c, d) {
		},
		b4DragDrop : function(c) {
		},
		onDragDrop : function(c, d) {
		},
		onInvalidDrop : function(c) {
		},
		b4EndDrag : function(c) {
		},
		endDrag : function(c) {
		},
		b4MouseDown : function(c) {
		},
		onMouseDown : function(c) {
		},
		onMouseUp : function(c) {
		},
		onAvailable : function() {
		},
		defaultPadding : {
			left : 0,
			right : 0,
			top : 0,
			bottom : 0
		},
		constrainTo : function(i, g, n) {
			if (typeof g == "number") {
				g = {
					left : g,
					right : g,
					top : g,
					bottom : g
				}
			}
			g = g || this.defaultPadding;
			var k = Ext.get(this.getEl()).getBox();
			var d = Ext.get(i);
			var m = d.getScroll();
			var j, e = d.dom;
			if (e == document.body) {
				j = {
					x : m.left,
					y : m.top,
					width : Ext.lib.Dom.getViewWidth(),
					height : Ext.lib.Dom.getViewHeight()
				}
			} else {
				var l = d.getXY();
				j = {
					x : l[0] + m.left,
					y : l[1] + m.top,
					width : e.clientWidth,
					height : e.clientHeight
				}
			}
			var h = k.y - j.y;
			var f = k.x - j.x;
			this.resetConstraints();
			this.setXConstraint(f - (g.left || 0), j.width - f - k.width - (g.right || 0), this.xTickSize);
			this.setYConstraint(h - (g.top || 0), j.height - h - k.height - (g.bottom || 0), this.yTickSize)
		},
		getEl : function() {
			if (!this._domRef) {
				this._domRef = Ext.getDom(this.id)
			}
			return this._domRef
		},
		getDragEl : function() {
			return Ext.getDom(this.dragElId)
		},
		init : function(e, c, d) {
			this.initTarget(e, c, d);
			a.on(this.id, "mousedown", this.handleMouseDown, this)
		},
		initTarget : function(e, c, d) {
			this.config = d || {};
			this.DDM = Ext.dd.DDM;
			this.groups = {};
			if (typeof e !== "string") {
				e = Ext.id(e)
			}
			this.id = e;
			this.addToGroup((c) ? c : "default");
			this.handleElId = e;
			this.setDragElId(e);
			this.invalidHandleTypes = {
				A : "A"
			};
			this.invalidHandleIds = {};
			this.invalidHandleClasses = [];
			this.applyConfig();
			this.handleOnAvailable()
		},
		applyConfig : function() {
			this.padding = this.config.padding || [ 0, 0, 0, 0 ];
			this.isTarget = (this.config.isTarget !== false);
			this.maintainOffset = (this.config.maintainOffset);
			this.primaryButtonOnly = (this.config.primaryButtonOnly !== false)
		},
		handleOnAvailable : function() {
			this.available = true;
			this.resetConstraints();
			this.onAvailable()
		},
		setPadding : function(e, c, f, d) {
			if (!c && 0 !== c) {
				this.padding = [ e, e, e, e ]
			} else {
				if (!f && 0 !== f) {
					this.padding = [ e, c, e, c ]
				} else {
					this.padding = [ e, c, f, d ]
				}
			}
		},
		setInitPosition : function(f, e) {
			var g = this.getEl();
			if (!this.DDM.verifyEl(g)) {
				return
			}
			var d = f || 0;
			var c = e || 0;
			var h = b.getXY(g);
			this.initPageX = h[0] - d;
			this.initPageY = h[1] - c;
			this.lastPageX = h[0];
			this.lastPageY = h[1];
			this.setStartPosition(h)
		},
		setStartPosition : function(d) {
			var c = d || b.getXY(this.getEl());
			this.deltaSetXY = null;
			this.startPageX = c[0];
			this.startPageY = c[1]
		},
		addToGroup : function(c) {
			this.groups[c] = true;
			this.DDM.regDragDrop(this, c)
		},
		removeFromGroup : function(c) {
			if (this.groups[c]) {
				delete this.groups[c]
			}
			this.DDM.removeDDFromGroup(this, c)
		},
		setDragElId : function(c) {
			this.dragElId = c
		},
		setHandleElId : function(c) {
			if (typeof c !== "string") {
				c = Ext.id(c)
			}
			this.handleElId = c;
			this.DDM.regHandle(this.id, c)
		},
		setOuterHandleElId : function(c) {
			if (typeof c !== "string") {
				c = Ext.id(c)
			}
			a.on(c, "mousedown", this.handleMouseDown, this);
			this.setHandleElId(c);
			this.hasOuterHandles = true
		},
		unreg : function() {
			a.un(this.id, "mousedown", this.handleMouseDown);
			this._domRef = null;
			this.DDM._remove(this)
		},
		destroy : function() {
			this.unreg()
		},
		isLocked : function() {
			return (this.DDM.isLocked() || this.locked)
		},
		handleMouseDown : function(f, d) {
			if (this.primaryButtonOnly && f.button != 0) {
				return
			}
			if (this.isLocked()) {
				return
			}
			this.DDM.refreshCache(this.groups);
			var c = new Ext.lib.Point(Ext.lib.Event.getPageX(f), Ext.lib.Event.getPageY(f));
			if (!this.hasOuterHandles && !this.DDM.isOverTarget(c, this)) {
			} else {
				if (this.clickValidator(f)) {
					this.setStartPosition();
					this.b4MouseDown(f);
					this.onMouseDown(f);
					this.DDM.handleMouseDown(f, this);
					this.DDM.stopEvent(f)
				} else {
				}
			}
		},
		clickValidator : function(d) {
			var c = d.getTarget();
			return (this.isValidHandleChild(c) && (this.id == this.handleElId || this.DDM.handleWasClicked(c, this.id)))
		},
		addInvalidHandleType : function(c) {
			var d = c.toUpperCase();
			this.invalidHandleTypes[d] = d
		},
		addInvalidHandleId : function(c) {
			if (typeof c !== "string") {
				c = Ext.id(c)
			}
			this.invalidHandleIds[c] = c
		},
		addInvalidHandleClass : function(c) {
			this.invalidHandleClasses.push(c)
		},
		removeInvalidHandleType : function(c) {
			var d = c.toUpperCase();
			delete this.invalidHandleTypes[d]
		},
		removeInvalidHandleId : function(c) {
			if (typeof c !== "string") {
				c = Ext.id(c)
			}
			delete this.invalidHandleIds[c]
		},
		removeInvalidHandleClass : function(d) {
			for ( var e = 0, c = this.invalidHandleClasses.length; e < c; ++e) {
				if (this.invalidHandleClasses[e] == d) {
					delete this.invalidHandleClasses[e]
				}
			}
		},
		isValidHandleChild : function(g) {
			var f = true;
			var j;
			try {
				j = g.nodeName.toUpperCase()
			} catch (h) {
				j = g.nodeName
			}
			f = f && !this.invalidHandleTypes[j];
			f = f && !this.invalidHandleIds[g.id];
			for ( var d = 0, c = this.invalidHandleClasses.length; f && d < c; ++d) {
				f = !Ext.fly(g).hasClass(this.invalidHandleClasses[d])
			}
			return f
		},
		setXTicks : function(f, c) {
			this.xTicks = [];
			this.xTickSize = c;
			var e = {};
			for ( var d = this.initPageX; d >= this.minX; d = d - c) {
				if (!e[d]) {
					this.xTicks[this.xTicks.length] = d;
					e[d] = true
				}
			}
			for (d = this.initPageX; d <= this.maxX; d = d + c) {
				if (!e[d]) {
					this.xTicks[this.xTicks.length] = d;
					e[d] = true
				}
			}
			this.xTicks.sort(this.DDM.numericSort)
		},
		setYTicks : function(f, c) {
			this.yTicks = [];
			this.yTickSize = c;
			var e = {};
			for ( var d = this.initPageY; d >= this.minY; d = d - c) {
				if (!e[d]) {
					this.yTicks[this.yTicks.length] = d;
					e[d] = true
				}
			}
			for (d = this.initPageY; d <= this.maxY; d = d + c) {
				if (!e[d]) {
					this.yTicks[this.yTicks.length] = d;
					e[d] = true
				}
			}
			this.yTicks.sort(this.DDM.numericSort)
		},
		setXConstraint : function(e, d, c) {
			this.leftConstraint = e;
			this.rightConstraint = d;
			this.minX = this.initPageX - e;
			this.maxX = this.initPageX + d;
			if (c) {
				this.setXTicks(this.initPageX, c)
			}
			this.constrainX = true
		},
		clearConstraints : function() {
			this.constrainX = false;
			this.constrainY = false;
			this.clearTicks()
		},
		clearTicks : function() {
			this.xTicks = null;
			this.yTicks = null;
			this.xTickSize = 0;
			this.yTickSize = 0
		},
		setYConstraint : function(c, e, d) {
			this.topConstraint = c;
			this.bottomConstraint = e;
			this.minY = this.initPageY - c;
			this.maxY = this.initPageY + e;
			if (d) {
				this.setYTicks(this.initPageY, d)
			}
			this.constrainY = true
		},
		resetConstraints : function() {
			if (this.initPageX || this.initPageX === 0) {
				var d = (this.maintainOffset) ? this.lastPageX - this.initPageX : 0;
				var c = (this.maintainOffset) ? this.lastPageY - this.initPageY : 0;
				this.setInitPosition(d, c)
			} else {
				this.setInitPosition()
			}
			if (this.constrainX) {
				this.setXConstraint(this.leftConstraint, this.rightConstraint, this.xTickSize)
			}
			if (this.constrainY) {
				this.setYConstraint(this.topConstraint, this.bottomConstraint, this.yTickSize)
			}
		},
		getTick : function(j, f) {
			if (!f) {
				return j
			} else {
				if (f[0] >= j) {
					return f[0]
				} else {
					for ( var d = 0, c = f.length; d < c; ++d) {
						var e = d + 1;
						if (f[e] && f[e] >= j) {
							var h = j - f[d];
							var g = f[e] - j;
							return (g > h) ? f[d] : f[e]
						}
					}
					return f[f.length - 1]
				}
			}
		},
		toString : function() {
			return ("DragDrop " + this.id)
		}
	}
})();
if (!Ext.dd.DragDropMgr) {
	Ext.dd.DragDropMgr = function() {
		var a = Ext.EventManager;
		return {
			ids : {},
			handleIds : {},
			dragCurrent : null,
			dragOvers : {},
			deltaX : 0,
			deltaY : 0,
			preventDefault : true,
			stopPropagation : true,
			initialized : false,
			locked : false,
			init : function() {
				this.initialized = true
			},
			POINT : 0,
			INTERSECT : 1,
			mode : 0,
			_execOnAll : function(d, c) {
				for ( var e in this.ids) {
					for ( var b in this.ids[e]) {
						var f = this.ids[e][b];
						if (!this.isTypeOfDD(f)) {
							continue
						}
						f[d].apply(f, c)
					}
				}
			},
			_onLoad : function() {
				this.init();
				a.on(document, "mouseup", this.handleMouseUp, this, true);
				a.on(document, "mousemove", this.handleMouseMove, this, true);
				a.on(window, "unload", this._onUnload, this, true);
				a.on(window, "resize", this._onResize, this, true)
			},
			_onResize : function(b) {
				this._execOnAll("resetConstraints", [])
			},
			lock : function() {
				this.locked = true
			},
			unlock : function() {
				this.locked = false
			},
			isLocked : function() {
				return this.locked
			},
			locationCache : {},
			useCache : true,
			clickPixelThresh : 3,
			clickTimeThresh : 350,
			dragThreshMet : false,
			clickTimeout : null,
			startX : 0,
			startY : 0,
			regDragDrop : function(c, b) {
				if (!this.initialized) {
					this.init()
				}
				if (!this.ids[b]) {
					this.ids[b] = {}
				}
				this.ids[b][c.id] = c
			},
			removeDDFromGroup : function(d, b) {
				if (!this.ids[b]) {
					this.ids[b] = {}
				}
				var c = this.ids[b];
				if (c && c[d.id]) {
					delete c[d.id]
				}
			},
			_remove : function(c) {
				for ( var b in c.groups) {
					if (b && this.ids[b] && this.ids[b][c.id]) {
						delete this.ids[b][c.id]
					}
				}
				delete this.handleIds[c.id]
			},
			regHandle : function(c, b) {
				if (!this.handleIds[c]) {
					this.handleIds[c] = {}
				}
				this.handleIds[c][b] = b
			},
			isDragDrop : function(b) {
				return (this.getDDById(b)) ? true : false
			},
			getRelated : function(g, c) {
				var f = [];
				for ( var e in g.groups) {
					for ( var d in this.ids[e]) {
						var b = this.ids[e][d];
						if (!this.isTypeOfDD(b)) {
							continue
						}
						if (!c || b.isTarget) {
							f[f.length] = b
						}
					}
				}
				return f
			},
			isLegalTarget : function(f, e) {
				var c = this.getRelated(f, true);
				for ( var d = 0, b = c.length; d < b; ++d) {
					if (c[d].id == e.id) {
						return true
					}
				}
				return false
			},
			isTypeOfDD : function(b) {
				return (b && b.__ygDragDrop)
			},
			isHandle : function(c, b) {
				return (this.handleIds[c] && this.handleIds[c][b])
			},
			getDDById : function(c) {
				for ( var b in this.ids) {
					if (this.ids[b][c]) {
						return this.ids[b][c]
					}
				}
				return null
			},
			handleMouseDown : function(d, c) {
				if (Ext.QuickTips) {
					Ext.QuickTips.disable()
				}
				if (this.dragCurrent) {
					this.handleMouseUp(d)
				}
				this.currentTarget = d.getTarget();
				this.dragCurrent = c;
				var b = c.getEl();
				this.startX = d.getPageX();
				this.startY = d.getPageY();
				this.deltaX = this.startX - b.offsetLeft;
				this.deltaY = this.startY - b.offsetTop;
				this.dragThreshMet = false;
				this.clickTimeout = setTimeout(function() {
					var e = Ext.dd.DDM;
					e.startDrag(e.startX, e.startY)
				}, this.clickTimeThresh)
			},
			startDrag : function(b, c) {
				clearTimeout(this.clickTimeout);
				if (this.dragCurrent) {
					this.dragCurrent.b4StartDrag(b, c);
					this.dragCurrent.startDrag(b, c)
				}
				this.dragThreshMet = true
			},
			handleMouseUp : function(b) {
				if (Ext.QuickTips) {
					Ext.QuickTips.enable()
				}
				if (!this.dragCurrent) {
					return
				}
				clearTimeout(this.clickTimeout);
				if (this.dragThreshMet) {
					this.fireEvents(b, true)
				} else {
				}
				this.stopDrag(b);
				this.stopEvent(b)
			},
			stopEvent : function(b) {
				if (this.stopPropagation) {
					b.stopPropagation()
				}
				if (this.preventDefault) {
					b.preventDefault()
				}
			},
			stopDrag : function(b) {
				if (this.dragCurrent) {
					if (this.dragThreshMet) {
						this.dragCurrent.b4EndDrag(b);
						this.dragCurrent.endDrag(b)
					}
					this.dragCurrent.onMouseUp(b)
				}
				this.dragCurrent = null;
				this.dragOvers = {}
			},
			handleMouseMove : function(d) {
				if (!this.dragCurrent) {
					return true
				}
				if (Ext.isIE && (d.button !== 0 && d.button !== 1 && d.button !== 2)) {
					this.stopEvent(d);
					return this.handleMouseUp(d)
				}
				if (!this.dragThreshMet) {
					var c = Math.abs(this.startX - d.getPageX());
					var b = Math.abs(this.startY - d.getPageY());
					if (c > this.clickPixelThresh || b > this.clickPixelThresh) {
						this.startDrag(this.startX, this.startY)
					}
				}
				if (this.dragThreshMet) {
					this.dragCurrent.b4Drag(d);
					this.dragCurrent.onDrag(d);
					if (!this.dragCurrent.moveOnly) {
						this.fireEvents(d, false)
					}
				}
				this.stopEvent(d);
				return true
			},
			fireEvents : function(m, n) {
				var p = this.dragCurrent;
				if (!p || p.isLocked()) {
					return
				}
				var q = m.getPoint();
				var b = [];
				var f = [];
				var k = [];
				var h = [];
				var d = [];
				for ( var g in this.dragOvers) {
					var c = this.dragOvers[g];
					if (!this.isTypeOfDD(c)) {
						continue
					}
					if (!this.isOverTarget(q, c, this.mode)) {
						f.push(c)
					}
					b[g] = true;
					delete this.dragOvers[g]
				}
				for ( var o in p.groups) {
					if ("string" != typeof o) {
						continue
					}
					for (g in this.ids[o]) {
						var j = this.ids[o][g];
						if (!this.isTypeOfDD(j)) {
							continue
						}
						if (j.isTarget && !j.isLocked() && ((j != p) || (p.ignoreSelf === false))) {
							if (this.isOverTarget(q, j, this.mode)) {
								if (n) {
									h.push(j)
								} else {
									if (!b[j.id]) {
										d.push(j)
									} else {
										k.push(j)
									}
									this.dragOvers[j.id] = j
								}
							}
						}
					}
				}
				if (this.mode) {
					if (f.length) {
						p.b4DragOut(m, f);
						p.onDragOut(m, f)
					}
					if (d.length) {
						p.onDragEnter(m, d)
					}
					if (k.length) {
						p.b4DragOver(m, k);
						p.onDragOver(m, k)
					}
					if (h.length) {
						p.b4DragDrop(m, h);
						p.onDragDrop(m, h)
					}
				} else {
					var l = 0;
					for (g = 0, l = f.length; g < l; ++g) {
						p.b4DragOut(m, f[g].id);
						p.onDragOut(m, f[g].id)
					}
					for (g = 0, l = d.length; g < l; ++g) {
						p.onDragEnter(m, d[g].id)
					}
					for (g = 0, l = k.length; g < l; ++g) {
						p.b4DragOver(m, k[g].id);
						p.onDragOver(m, k[g].id)
					}
					for (g = 0, l = h.length; g < l; ++g) {
						p.b4DragDrop(m, h[g].id);
						p.onDragDrop(m, h[g].id)
					}
				}
				if (n && !h.length) {
					p.onInvalidDrop(m)
				}
			},
			getBestMatch : function(d) {
				var f = null;
				var c = d.length;
				if (c == 1) {
					f = d[0]
				} else {
					for ( var e = 0; e < c; ++e) {
						var b = d[e];
						if (b.cursorIsOver) {
							f = b;
							break
						} else {
							if (!f || f.overlap.getArea() < b.overlap.getArea()) {
								f = b
							}
						}
					}
				}
				return f
			},
			refreshCache : function(c) {
				for ( var b in c) {
					if ("string" != typeof b) {
						continue
					}
					for ( var d in this.ids[b]) {
						var e = this.ids[b][d];
						if (this.isTypeOfDD(e)) {
							var f = this.getLocation(e);
							if (f) {
								this.locationCache[e.id] = f
							} else {
								delete this.locationCache[e.id]
							}
						}
					}
				}
			},
			verifyEl : function(c) {
				if (c) {
					var b;
					if (Ext.isIE) {
						try {
							b = c.offsetParent
						} catch (d) {
						}
					} else {
						b = c.offsetParent
					}
					if (b) {
						return true
					}
				}
				return false
			},
			getLocation : function(i) {
				if (!this.isTypeOfDD(i)) {
					return null
				}
				var g = i.getEl(), m, f, d, o, n, p, c, k, h;
				try {
					m = Ext.lib.Dom.getXY(g)
				} catch (j) {
				}
				if (!m) {
					return null
				}
				f = m[0];
				d = f + g.offsetWidth;
				o = m[1];
				n = o + g.offsetHeight;
				p = o - i.padding[0];
				c = d + i.padding[1];
				k = n + i.padding[2];
				h = f - i.padding[3];
				return new Ext.lib.Region(p, c, k, h)
			},
			isOverTarget : function(j, b, d) {
				var f = this.locationCache[b.id];
				if (!f || !this.useCache) {
					f = this.getLocation(b);
					this.locationCache[b.id] = f
				}
				if (!f) {
					return false
				}
				b.cursorIsOver = f.contains(j);
				var i = this.dragCurrent;
				if (!i || !i.getTargetCoord || (!d && !i.constrainX && !i.constrainY)) {
					return b.cursorIsOver
				}
				b.overlap = null;
				var g = i.getTargetCoord(j.x, j.y);
				var c = i.getDragEl();
				var e = new Ext.lib.Region(g.y, g.x + c.offsetWidth, g.y + c.offsetHeight, g.x);
				var h = e.intersect(f);
				if (h) {
					b.overlap = h;
					return (d) ? true : b.cursorIsOver
				} else {
					return false
				}
			},
			_onUnload : function(c, b) {
				Ext.dd.DragDropMgr.unregAll()
			},
			unregAll : function() {
				if (this.dragCurrent) {
					this.stopDrag();
					this.dragCurrent = null
				}
				this._execOnAll("unreg", []);
				for ( var b in this.elementCache) {
					delete this.elementCache[b]
				}
				this.elementCache = {};
				this.ids = {}
			},
			elementCache : {},
			getElWrapper : function(c) {
				var b = this.elementCache[c];
				if (!b || !b.el) {
					b = this.elementCache[c] = new this.ElementWrapper(Ext.getDom(c))
				}
				return b
			},
			getElement : function(b) {
				return Ext.getDom(b)
			},
			getCss : function(c) {
				var b = Ext.getDom(c);
				return (b) ? b.style : null
			},
			ElementWrapper : function(b) {
				this.el = b || null;
				this.id = this.el && b.id;
				this.css = this.el && b.style
			},
			getPosX : function(b) {
				return Ext.lib.Dom.getX(b)
			},
			getPosY : function(b) {
				return Ext.lib.Dom.getY(b)
			},
			swapNode : function(d, b) {
				if (d.swapNode) {
					d.swapNode(b)
				} else {
					var e = b.parentNode;
					var c = b.nextSibling;
					if (c == d) {
						e.insertBefore(d, b)
					} else {
						if (b == d.nextSibling) {
							e.insertBefore(b, d)
						} else {
							d.parentNode.replaceChild(b, d);
							e.insertBefore(d, c)
						}
					}
				}
			},
			getScroll : function() {
				var d, b, e = document.documentElement, c = document.body;
				if (e && (e.scrollTop || e.scrollLeft)) {
					d = e.scrollTop;
					b = e.scrollLeft
				} else {
					if (c) {
						d = c.scrollTop;
						b = c.scrollLeft
					} else {
					}
				}
				return {
					top : d,
					left : b
				}
			},
			getStyle : function(c, b) {
				return Ext.fly(c).getStyle(b)
			},
			getScrollTop : function() {
				return this.getScroll().top
			},
			getScrollLeft : function() {
				return this.getScroll().left
			},
			moveToEl : function(b, d) {
				var c = Ext.lib.Dom.getXY(d);
				Ext.lib.Dom.setXY(b, c)
			},
			numericSort : function(d, c) {
				return (d - c)
			},
			_timeoutCount : 0,
			_addListeners : function() {
				var b = Ext.dd.DDM;
				if (Ext.lib.Event && document) {
					b._onLoad()
				} else {
					if (b._timeoutCount > 2000) {
					} else {
						setTimeout(b._addListeners, 10);
						if (document && document.body) {
							b._timeoutCount += 1
						}
					}
				}
			},
			handleWasClicked : function(b, d) {
				if (this.isHandle(d, b.id)) {
					return true
				} else {
					var c = b.parentNode;
					while (c) {
						if (this.isHandle(d, c.id)) {
							return true
						} else {
							c = c.parentNode
						}
					}
				}
				return false
			}
		}
	}();
	Ext.dd.DDM = Ext.dd.DragDropMgr;
	Ext.dd.DDM._addListeners()
}
Ext.dd.DD = function(c, a, b) {
	if (c) {
		this.init(c, a, b)
	}
};
Ext.extend(Ext.dd.DD, Ext.dd.DragDrop, {
	scroll : true,
	autoOffset : function(c, b) {
		var a = c - this.startPageX;
		var d = b - this.startPageY;
		this.setDelta(a, d)
	},
	setDelta : function(b, a) {
		this.deltaX = b;
		this.deltaY = a
	},
	setDragElPos : function(c, b) {
		var a = this.getDragEl();
		this.alignElWithMouse(a, c, b)
	},
	alignElWithMouse : function(c, g, f) {
		var e = this.getTargetCoord(g, f);
		var b = c.dom ? c : Ext.fly(c, "_dd");
		if (!this.deltaSetXY) {
			var h = [ e.x, e.y ];
			b.setXY(h);
			var d = b.getLeft(true);
			var a = b.getTop(true);
			this.deltaSetXY = [ d - e.x, a - e.y ]
		} else {
			b.setLeftTop(e.x + this.deltaSetXY[0], e.y + this.deltaSetXY[1])
		}
		this.cachePosition(e.x, e.y);
		this.autoScroll(e.x, e.y, c.offsetHeight, c.offsetWidth);
		return e
	},
	cachePosition : function(b, a) {
		if (b) {
			this.lastPageX = b;
			this.lastPageY = a
		} else {
			var c = Ext.lib.Dom.getXY(this.getEl());
			this.lastPageX = c[0];
			this.lastPageY = c[1]
		}
	},
	autoScroll : function(k, j, e, l) {
		if (this.scroll) {
			var m = Ext.lib.Dom.getViewHeight();
			var b = Ext.lib.Dom.getViewWidth();
			var o = this.DDM.getScrollTop();
			var d = this.DDM.getScrollLeft();
			var i = e + j;
			var n = l + k;
			var g = (m + o - j - this.deltaY);
			var f = (b + d - k - this.deltaX);
			var c = 40;
			var a = (document.all) ? 80 : 30;
			if (i > m && g < c) {
				window.scrollTo(d, o + a)
			}
			if (j < o && o > 0 && j - o < c) {
				window.scrollTo(d, o - a)
			}
			if (n > b && f < c) {
				window.scrollTo(d + a, o)
			}
			if (k < d && d > 0 && k - d < c) {
				window.scrollTo(d - a, o)
			}
		}
	},
	getTargetCoord : function(c, b) {
		var a = c - this.deltaX;
		var d = b - this.deltaY;
		if (this.constrainX) {
			if (a < this.minX) {
				a = this.minX
			}
			if (a > this.maxX) {
				a = this.maxX
			}
		}
		if (this.constrainY) {
			if (d < this.minY) {
				d = this.minY
			}
			if (d > this.maxY) {
				d = this.maxY
			}
		}
		a = this.getTick(a, this.xTicks);
		d = this.getTick(d, this.yTicks);
		return {
			x : a,
			y : d
		}
	},
	applyConfig : function() {
		Ext.dd.DD.superclass.applyConfig.call(this);
		this.scroll = (this.config.scroll !== false)
	},
	b4MouseDown : function(a) {
		this.autoOffset(a.getPageX(), a.getPageY())
	},
	b4Drag : function(a) {
		this.setDragElPos(a.getPageX(), a.getPageY())
	},
	toString : function() {
		return ("DD " + this.id)
	}
});
Ext.dd.DDProxy = function(c, a, b) {
	if (c) {
		this.init(c, a, b);
		this.initFrame()
	}
};
Ext.dd.DDProxy.dragElId = "ygddfdiv";
Ext.extend(Ext.dd.DDProxy, Ext.dd.DD, {
	resizeFrame : true,
	centerFrame : false,
	createFrame : function() {
		var b = this;
		var a = document.body;
		if (!a || !a.firstChild) {
			setTimeout(function() {
				b.createFrame()
			}, 50);
			return
		}
		var d = this.getDragEl();
		if (!d) {
			d = document.createElement("div");
			d.id = this.dragElId;
			var c = d.style;
			c.position = "absolute";
			c.visibility = "hidden";
			c.cursor = "move";
			c.border = "2px solid #aaa";
			c.zIndex = 999;
			a.insertBefore(d, a.firstChild)
		}
	},
	initFrame : function() {
		this.createFrame()
	},
	applyConfig : function() {
		Ext.dd.DDProxy.superclass.applyConfig.call(this);
		this.resizeFrame = (this.config.resizeFrame !== false);
		this.centerFrame = (this.config.centerFrame);
		this.setDragElId(this.config.dragElId || Ext.dd.DDProxy.dragElId)
	},
	showFrame : function(e, d) {
		var c = this.getEl();
		var a = this.getDragEl();
		var b = a.style;
		this._resizeProxy();
		if (this.centerFrame) {
			this.setDelta(Math.round(parseInt(b.width, 10) / 2), Math.round(parseInt(b.height, 10) / 2))
		}
		this.setDragElPos(e, d);
		Ext.fly(a).show()
	},
	_resizeProxy : function() {
		if (this.resizeFrame) {
			var a = this.getEl();
			Ext.fly(this.getDragEl()).setSize(a.offsetWidth, a.offsetHeight)
		}
	},
	b4MouseDown : function(b) {
		var a = b.getPageX();
		var c = b.getPageY();
		this.autoOffset(a, c);
		this.setDragElPos(a, c)
	},
	b4StartDrag : function(a, b) {
		this.showFrame(a, b)
	},
	b4EndDrag : function(a) {
		Ext.fly(this.getDragEl()).hide()
	},
	endDrag : function(c) {
		var b = this.getEl();
		var a = this.getDragEl();
		a.style.visibility = "";
		this.beforeMove();
		b.style.visibility = "hidden";
		Ext.dd.DDM.moveToEl(b, a);
		a.style.visibility = "hidden";
		b.style.visibility = "";
		this.afterDrag()
	},
	beforeMove : function() {
	},
	afterDrag : function() {
	},
	toString : function() {
		return ("DDProxy " + this.id)
	}
});
Ext.dd.DDTarget = function(c, a, b) {
	if (c) {
		this.initTarget(c, a, b)
	}
};
Ext.extend(Ext.dd.DDTarget, Ext.dd.DragDrop, {
	toString : function() {
		return ("DDTarget " + this.id)
	}
});
Ext.dd.DragTracker = function(a) {
	Ext.apply(this, a);
	this.addEvents("mousedown", "mouseup", "mousemove", "dragstart", "dragend", "drag");
	this.dragRegion = new Ext.lib.Region(0, 0, 0, 0);
	if (this.el) {
		this.initEl(this.el)
	}
};
Ext.extend(Ext.dd.DragTracker, Ext.util.Observable, {
	active : false,
	tolerance : 5,
	autoStart : false,
	initEl : function(a) {
		this.el = Ext.get(a);
		a.on("mousedown", this.onMouseDown, this, this.delegate ? {
			delegate : this.delegate
		} : undefined)
	},
	destroy : function() {
		this.el.un("mousedown", this.onMouseDown, this)
	},
	onMouseDown : function(c, b) {
		if (this.fireEvent("mousedown", this, c) !== false && this.onBeforeStart(c) !== false) {
			this.startXY = this.lastXY = c.getXY();
			this.dragTarget = this.delegate ? b : this.el.dom;
			if (this.preventDefault !== false) {
				c.preventDefault()
			}
			var a = Ext.getDoc();
			a.on("mouseup", this.onMouseUp, this);
			a.on("mousemove", this.onMouseMove, this);
			a.on("selectstart", this.stopSelect, this);
			if (this.autoStart) {
				this.timer = this.triggerStart.defer(this.autoStart === true ? 1000 : this.autoStart, this)
			}
		}
	},
	onMouseMove : function(d, c) {
		if (this.active && Ext.isIE && !d.browserEvent.button) {
			d.preventDefault();
			this.onMouseUp(d);
			return
		}
		d.preventDefault();
		var b = d.getXY(), a = this.startXY;
		this.lastXY = b;
		if (!this.active) {
			if (Math.abs(a[0] - b[0]) > this.tolerance || Math.abs(a[1] - b[1]) > this.tolerance) {
				this.triggerStart()
			} else {
				return
			}
		}
		this.fireEvent("mousemove", this, d);
		this.onDrag(d);
		this.fireEvent("drag", this, d)
	},
	onMouseUp : function(c) {
		var b = Ext.getDoc();
		b.un("mousemove", this.onMouseMove, this);
		b.un("mouseup", this.onMouseUp, this);
		b.un("selectstart", this.stopSelect, this);
		c.preventDefault();
		this.clearStart();
		var a = this.active;
		this.active = false;
		delete this.elRegion;
		this.fireEvent("mouseup", this, c);
		if (a) {
			this.onEnd(c);
			this.fireEvent("dragend", this, c)
		}
	},
	triggerStart : function(a) {
		this.clearStart();
		this.active = true;
		this.onStart(this.startXY);
		this.fireEvent("dragstart", this, this.startXY)
	},
	clearStart : function() {
		if (this.timer) {
			clearTimeout(this.timer);
			delete this.timer
		}
	},
	stopSelect : function(a) {
		a.stopEvent();
		return false
	},
	onBeforeStart : function(a) {
	},
	onStart : function(a) {
	},
	onDrag : function(a) {
	},
	onEnd : function(a) {
	},
	getDragTarget : function() {
		return this.dragTarget
	},
	getDragCt : function() {
		return this.el
	},
	getXY : function(a) {
		return a ? this.constrainModes[a].call(this, this.lastXY) : this.lastXY
	},
	getOffset : function(c) {
		var b = this.getXY(c);
		var a = this.startXY;
		return [ a[0] - b[0], a[1] - b[1] ]
	},
	constrainModes : {
		point : function(b) {
			if (!this.elRegion) {
				this.elRegion = this.getDragCt().getRegion()
			}
			var a = this.dragRegion;
			a.left = b[0];
			a.top = b[1];
			a.right = b[0];
			a.bottom = b[1];
			a.constrainTo(this.elRegion);
			return [ a.left, a.top ]
		}
	}
});
Ext.dd.ScrollManager = function() {
	var c = Ext.dd.DragDropMgr;
	var e = {};
	var b = null;
	var h = {};
	var g = function(k) {
		b = null;
		a()
	};
	var i = function() {
		if (c.dragCurrent) {
			c.refreshCache(c.dragCurrent.groups)
		}
	};
	var d = function() {
		if (c.dragCurrent) {
			var k = Ext.dd.ScrollManager;
			var l = h.el.ddScrollConfig ? h.el.ddScrollConfig.increment : k.increment;
			if (!k.animate) {
				if (h.el.scroll(h.dir, l)) {
					i()
				}
			} else {
				h.el.scroll(h.dir, l, true, k.animDuration, i)
			}
		}
	};
	var a = function() {
		if (h.id) {
			clearInterval(h.id)
		}
		h.id = 0;
		h.el = null;
		h.dir = ""
	};
	var f = function(l, k) {
		a();
		h.el = l;
		h.dir = k;
		var m = (l.ddScrollConfig && l.ddScrollConfig.frequency) ? l.ddScrollConfig.frequency
				: Ext.dd.ScrollManager.frequency;
		h.id = setInterval(d, m)
	};
	var j = function(n, p) {
		if (p || !c.dragCurrent) {
			return
		}
		var q = Ext.dd.ScrollManager;
		if (!b || b != c.dragCurrent) {
			b = c.dragCurrent;
			q.refreshCache()
		}
		var s = Ext.lib.Event.getXY(n);
		var t = new Ext.lib.Point(s[0], s[1]);
		for ( var l in e) {
			var m = e[l], k = m._region;
			var o = m.ddScrollConfig ? m.ddScrollConfig : q;
			if (k && k.contains(t) && m.isScrollable()) {
				if (k.bottom - t.y <= o.vthresh) {
					if (h.el != m) {
						f(m, "down")
					}
					return
				} else {
					if (k.right - t.x <= o.hthresh) {
						if (h.el != m) {
							f(m, "left")
						}
						return
					} else {
						if (t.y - k.top <= o.vthresh) {
							if (h.el != m) {
								f(m, "up")
							}
							return
						} else {
							if (t.x - k.left <= o.hthresh) {
								if (h.el != m) {
									f(m, "right")
								}
								return
							}
						}
					}
				}
			}
		}
		a()
	};
	c.fireEvents = c.fireEvents.createSequence(j, c);
	c.stopDrag = c.stopDrag.createSequence(g, c);
	return {
		register : function(m) {
			if (Ext.isArray(m)) {
				for ( var l = 0, k = m.length; l < k; l++) {
					this.register(m[l])
				}
			} else {
				m = Ext.get(m);
				e[m.id] = m
			}
		},
		unregister : function(m) {
			if (Ext.isArray(m)) {
				for ( var l = 0, k = m.length; l < k; l++) {
					this.unregister(m[l])
				}
			} else {
				m = Ext.get(m);
				delete e[m.id]
			}
		},
		vthresh : 25,
		hthresh : 25,
		increment : 100,
		frequency : 500,
		animate : true,
		animDuration : 0.4,
		refreshCache : function() {
			for ( var k in e) {
				if (typeof e[k] == "object") {
					e[k]._region = e[k].getRegion()
				}
			}
		}
	}
}();
Ext.dd.Registry = function() {
	var d = {};
	var b = {};
	var a = 0;
	var c = function(f, e) {
		if (typeof f == "string") {
			return f
		}
		var g = f.id;
		if (!g && e !== false) {
			g = "extdd-" + (++a);
			f.id = g
		}
		return g
	};
	return {
		register : function(h, j) {
			j = j || {};
			if (typeof h == "string") {
				h = document.getElementById(h)
			}
			j.ddel = h;
			d[c(h)] = j;
			if (j.isHandle !== false) {
				b[j.ddel.id] = j
			}
			if (j.handles) {
				var g = j.handles;
				for ( var f = 0, e = g.length; f < e; f++) {
					b[c(g[f])] = j
				}
			}
		},
		unregister : function(h) {
			var k = c(h, false);
			var j = d[k];
			if (j) {
				delete d[k];
				if (j.handles) {
					var g = j.handles;
					for ( var f = 0, e = g.length; f < e; f++) {
						delete b[c(g[f], false)]
					}
				}
			}
		},
		getHandle : function(e) {
			if (typeof e != "string") {
				e = e.id
			}
			return b[e]
		},
		getHandleFromEvent : function(g) {
			var f = Ext.lib.Event.getTarget(g);
			return f ? b[f.id] : null
		},
		getTarget : function(e) {
			if (typeof e != "string") {
				e = e.id
			}
			return d[e]
		},
		getTargetFromEvent : function(g) {
			var f = Ext.lib.Event.getTarget(g);
			return f ? d[f.id] || b[f.id] : null
		}
	}
}();
Ext.dd.StatusProxy = function(a) {
	Ext.apply(this, a);
	this.id = this.id || Ext.id();
	this.el = new Ext.Layer({
		dh : {
			id : this.id,
			tag : "div",
			cls : "x-dd-drag-proxy " + this.dropNotAllowed,
			children : [ {
				tag : "div",
				cls : "x-dd-drop-icon"
			}, {
				tag : "div",
				cls : "x-dd-drag-ghost"
			} ]
		},
		shadow : !a || a.shadow !== false
	});
	this.ghost = Ext.get(this.el.dom.childNodes[1]);
	this.dropStatus = this.dropNotAllowed
};
Ext.dd.StatusProxy.prototype = {
	dropAllowed : "x-dd-drop-ok",
	dropNotAllowed : "x-dd-drop-nodrop",
	setStatus : function(a) {
		a = a || this.dropNotAllowed;
		if (this.dropStatus != a) {
			this.el.replaceClass(this.dropStatus, a);
			this.dropStatus = a
		}
	},
	reset : function(a) {
		this.el.dom.className = "x-dd-drag-proxy " + this.dropNotAllowed;
		this.dropStatus = this.dropNotAllowed;
		if (a) {
			this.ghost.update("")
		}
	},
	update : function(a) {
		if (typeof a == "string") {
			this.ghost.update(a)
		} else {
			this.ghost.update("");
			a.style.margin = "0";
			this.ghost.dom.appendChild(a)
		}
		var b = this.ghost.dom.firstChild;
		if (b) {
			Ext.fly(b).setStyle("float", "none")
		}
	},
	getEl : function() {
		return this.el
	},
	getGhost : function() {
		return this.ghost
	},
	hide : function(a) {
		this.el.hide();
		if (a) {
			this.reset(true)
		}
	},
	stop : function() {
		if (this.anim && this.anim.isAnimated && this.anim.isAnimated()) {
			this.anim.stop()
		}
	},
	show : function() {
		this.el.show()
	},
	sync : function() {
		this.el.sync()
	},
	repair : function(b, c, a) {
		this.callback = c;
		this.scope = a;
		if (b && this.animRepair !== false) {
			this.el.addClass("x-dd-drag-repair");
			this.el.hideUnders(true);
			this.anim = this.el.shift({
				duration : this.repairDuration || 0.5,
				easing : "easeOut",
				xy : b,
				stopFx : true,
				callback : this.afterRepair,
				scope : this
			})
		} else {
			this.afterRepair()
		}
	},
	afterRepair : function() {
		this.hide(true);
		if (typeof this.callback == "function") {
			this.callback.call(this.scope || this)
		}
		this.callback = null;
		this.scope = null
	}
};
Ext.dd.DragSource = function(b, a) {
	this.el = Ext.get(b);
	if (!this.dragData) {
		this.dragData = {}
	}
	Ext.apply(this, a);
	if (!this.proxy) {
		this.proxy = new Ext.dd.StatusProxy()
	}
	Ext.dd.DragSource.superclass.constructor.call(this, this.el.dom, this.ddGroup || this.group, {
		dragElId : this.proxy.id,
		resizeFrame : false,
		isTarget : false,
		scroll : this.scroll === true
	});
	this.dragging = false
};
Ext.extend(Ext.dd.DragSource, Ext.dd.DDProxy, {
	dropAllowed : "x-dd-drop-ok",
	dropNotAllowed : "x-dd-drop-nodrop",
	getDragData : function(a) {
		return this.dragData
	},
	onDragEnter : function(c, d) {
		var b = Ext.dd.DragDropMgr.getDDById(d);
		this.cachedTarget = b;
		if (this.beforeDragEnter(b, c, d) !== false) {
			if (b.isNotifyTarget) {
				var a = b.notifyEnter(this, c, this.dragData);
				this.proxy.setStatus(a)
			} else {
				this.proxy.setStatus(this.dropAllowed)
			}
			if (this.afterDragEnter) {
				this.afterDragEnter(b, c, d)
			}
		}
	},
	beforeDragEnter : function(b, a, c) {
		return true
	},
	alignElWithMouse : function() {
		Ext.dd.DragSource.superclass.alignElWithMouse.apply(this, arguments);
		this.proxy.sync()
	},
	onDragOver : function(c, d) {
		var b = this.cachedTarget || Ext.dd.DragDropMgr.getDDById(d);
		if (this.beforeDragOver(b, c, d) !== false) {
			if (b.isNotifyTarget) {
				var a = b.notifyOver(this, c, this.dragData);
				this.proxy.setStatus(a)
			}
			if (this.afterDragOver) {
				this.afterDragOver(b, c, d)
			}
		}
	},
	beforeDragOver : function(b, a, c) {
		return true
	},
	onDragOut : function(b, c) {
		var a = this.cachedTarget || Ext.dd.DragDropMgr.getDDById(c);
		if (this.beforeDragOut(a, b, c) !== false) {
			if (a.isNotifyTarget) {
				a.notifyOut(this, b, this.dragData)
			}
			this.proxy.reset();
			if (this.afterDragOut) {
				this.afterDragOut(a, b, c)
			}
		}
		this.cachedTarget = null
	},
	beforeDragOut : function(b, a, c) {
		return true
	},
	onDragDrop : function(b, c) {
		var a = this.cachedTarget || Ext.dd.DragDropMgr.getDDById(c);
		if (this.beforeDragDrop(a, b, c) !== false) {
			if (a.isNotifyTarget) {
				if (a.notifyDrop(this, b, this.dragData)) {
					this.onValidDrop(a, b, c)
				} else {
					this.onInvalidDrop(a, b, c)
				}
			} else {
				this.onValidDrop(a, b, c)
			}
			if (this.afterDragDrop) {
				this.afterDragDrop(a, b, c)
			}
		}
		delete this.cachedTarget
	},
	beforeDragDrop : function(b, a, c) {
		return true
	},
	onValidDrop : function(b, a, c) {
		this.hideProxy();
		if (this.afterValidDrop) {
			this.afterValidDrop(b, a, c)
		}
	},
	getRepairXY : function(b, a) {
		return this.el.getXY()
	},
	onInvalidDrop : function(b, a, c) {
		this.beforeInvalidDrop(b, a, c);
		if (this.cachedTarget) {
			if (this.cachedTarget.isNotifyTarget) {
				this.cachedTarget.notifyOut(this, a, this.dragData)
			}
			this.cacheTarget = null
		}
		this.proxy.repair(this.getRepairXY(a, this.dragData), this.afterRepair, this);
		if (this.afterInvalidDrop) {
			this.afterInvalidDrop(a, c)
		}
	},
	afterRepair : function() {
		if (Ext.enableFx) {
			this.el.highlight(this.hlColor || "c3daf9")
		}
		this.dragging = false
	},
	beforeInvalidDrop : function(b, a, c) {
		return true
	},
	handleMouseDown : function(b) {
		if (this.dragging) {
			return
		}
		var a = this.getDragData(b);
		if (a && this.onBeforeDrag(a, b) !== false) {
			this.dragData = a;
			this.proxy.stop();
			Ext.dd.DragSource.superclass.handleMouseDown.apply(this, arguments)
		}
	},
	onBeforeDrag : function(a, b) {
		return true
	},
	onStartDrag : Ext.emptyFn,
	startDrag : function(a, b) {
		this.proxy.reset();
		this.dragging = true;
		this.proxy.update("");
		this.onInitDrag(a, b);
		this.proxy.show()
	},
	onInitDrag : function(a, c) {
		var b = this.el.dom.cloneNode(true);
		b.id = Ext.id();
		this.proxy.update(b);
		this.onStartDrag(a, c);
		return true
	},
	getProxy : function() {
		return this.proxy
	},
	hideProxy : function() {
		this.proxy.hide();
		this.proxy.reset(true);
		this.dragging = false
	},
	triggerCacheRefresh : function() {
		Ext.dd.DDM.refreshCache(this.groups)
	},
	b4EndDrag : function(a) {
	},
	endDrag : function(a) {
		this.onEndDrag(this.dragData, a)
	},
	onEndDrag : function(a, b) {
	},
	autoOffset : function(a, b) {
		this.setDelta(-12, -20)
	}
});
Ext.dd.DropTarget = function(b, a) {
	this.el = Ext.get(b);
	Ext.apply(this, a);
	if (this.containerScroll) {
		Ext.dd.ScrollManager.register(this.el)
	}
	Ext.dd.DropTarget.superclass.constructor.call(this, this.el.dom, this.ddGroup || this.group, {
		isTarget : true
	})
};
Ext.extend(Ext.dd.DropTarget, Ext.dd.DDTarget, {
	dropAllowed : "x-dd-drop-ok",
	dropNotAllowed : "x-dd-drop-nodrop",
	isTarget : true,
	isNotifyTarget : true,
	notifyEnter : function(a, c, b) {
		if (this.overClass) {
			this.el.addClass(this.overClass)
		}
		return this.dropAllowed
	},
	notifyOver : function(a, c, b) {
		return this.dropAllowed
	},
	notifyOut : function(a, c, b) {
		if (this.overClass) {
			this.el.removeClass(this.overClass)
		}
	},
	notifyDrop : function(a, c, b) {
		return false
	}
});
Ext.dd.DragZone = function(b, a) {
	Ext.dd.DragZone.superclass.constructor.call(this, b, a);
	if (this.containerScroll) {
		Ext.dd.ScrollManager.register(this.el)
	}
};
Ext.extend(Ext.dd.DragZone, Ext.dd.DragSource, {
	getDragData : function(a) {
		return Ext.dd.Registry.getHandleFromEvent(a)
	},
	onInitDrag : function(a, b) {
		this.proxy.update(this.dragData.ddel.cloneNode(true));
		this.onStartDrag(a, b);
		return true
	},
	afterRepair : function() {
		if (Ext.enableFx) {
			Ext.Element.fly(this.dragData.ddel).highlight(this.hlColor || "c3daf9")
		}
		this.dragging = false
	},
	getRepairXY : function(a) {
		return Ext.Element.fly(this.dragData.ddel).getXY()
	}
});
Ext.dd.DropZone = function(b, a) {
	Ext.dd.DropZone.superclass.constructor.call(this, b, a)
};
Ext.extend(Ext.dd.DropZone, Ext.dd.DropTarget, {
	getTargetFromEvent : function(a) {
		return Ext.dd.Registry.getTargetFromEvent(a)
	},
	onNodeEnter : function(d, a, c, b) {
	},
	onNodeOver : function(d, a, c, b) {
		return this.dropAllowed
	},
	onNodeOut : function(d, a, c, b) {
	},
	onNodeDrop : function(d, a, c, b) {
		return false
	},
	onContainerOver : function(a, c, b) {
		return this.dropNotAllowed
	},
	onContainerDrop : function(a, c, b) {
		return false
	},
	notifyEnter : function(a, c, b) {
		return this.dropNotAllowed
	},
	notifyOver : function(a, c, b) {
		var d = this.getTargetFromEvent(c);
		if (!d) {
			if (this.lastOverNode) {
				this.onNodeOut(this.lastOverNode, a, c, b);
				this.lastOverNode = null
			}
			return this.onContainerOver(a, c, b)
		}
		if (this.lastOverNode != d) {
			if (this.lastOverNode) {
				this.onNodeOut(this.lastOverNode, a, c, b)
			}
			this.onNodeEnter(d, a, c, b);
			this.lastOverNode = d
		}
		return this.onNodeOver(d, a, c, b)
	},
	notifyOut : function(a, c, b) {
		if (this.lastOverNode) {
			this.onNodeOut(this.lastOverNode, a, c, b);
			this.lastOverNode = null
		}
	},
	notifyDrop : function(a, c, b) {
		if (this.lastOverNode) {
			this.onNodeOut(this.lastOverNode, a, c, b);
			this.lastOverNode = null
		}
		var d = this.getTargetFromEvent(c);
		return d ? this.onNodeDrop(d, a, c, b) : this.onContainerDrop(a, c, b)
	},
	triggerCacheRefresh : function() {
		Ext.dd.DDM.refreshCache(this.groups)
	}
});
Ext.Element.addMethods({
	initDD : function(c, b, d) {
		var a = new Ext.dd.DD(Ext.id(this.dom), c, b);
		return Ext.apply(a, d)
	},
	initDDProxy : function(c, b, d) {
		var a = new Ext.dd.DDProxy(Ext.id(this.dom), c, b);
		return Ext.apply(a, d)
	},
	initDDTarget : function(c, b, d) {
		var a = new Ext.dd.DDTarget(Ext.id(this.dom), c, b);
		return Ext.apply(a, d)
	}
});
Ext.layout.MenuLayout = Ext.extend(Ext.layout.ContainerLayout, {
	monitorResize : true,
	setContainer : function(a) {
		this.monitorResize = !a.floating;
		Ext.layout.MenuLayout.superclass.setContainer.call(this, a)
	},
	renderItem : function(f, b, e) {
		if (!this.itemTpl) {
			this.itemTpl = Ext.layout.MenuLayout.prototype.itemTpl = new Ext.XTemplate(
					'<li id="{itemId}" class="{itemCls}">', '<tpl if="needsIcon">',
					'<img src="{icon}" class="{iconCls}"/>', "</tpl>", "</li>")
		}
		if (f && !f.rendered) {
			if (Ext.isNumber(b)) {
				b = e.dom.childNodes[b]
			}
			var d = this.getItemArgs(f);
			f.render(f.positionEl = b ? this.itemTpl.insertBefore(b, d, true) : this.itemTpl.append(e, d, true));
			f.positionEl.menuItemId = f.itemId || f.id;
			if (!d.isMenuItem && d.needsIcon) {
				f.positionEl.addClass("x-menu-list-item-indent")
			}
		} else {
			if (f && !this.isValidParent(f, e)) {
				if (Ext.isNumber(b)) {
					b = e.dom.childNodes[b]
				}
				e.dom.insertBefore(f.getActionEl().dom, b || null)
			}
		}
	},
	getItemArgs : function(b) {
		var a = b instanceof Ext.menu.Item;
		return {
			isMenuItem : a,
			needsIcon : !a && (b.icon || b.iconCls),
			icon : b.icon || Ext.BLANK_IMAGE_URL,
			iconCls : "x-menu-item-icon " + (b.iconCls || ""),
			itemId : "x-menu-el-" + b.id,
			itemCls : "x-menu-list-item " + (this.extraCls || "")
		}
	},
	isValidParent : function(b, a) {
		return b.el.up("li.x-menu-list-item", 5).dom.parentNode === (a.dom || a)
	},
	onLayout : function(a, b) {
		this.renderAll(a, b);
		this.doAutoSize()
	},
	doAutoSize : function() {
		var c = this.container, a = c.width;
		if (c.floating) {
			if (a) {
				c.setWidth(a)
			} else {
				if (Ext.isIE) {
					c.setWidth(Ext.isStrict && (Ext.isIE7 || Ext.isIE8) ? "auto" : c.minWidth);
					var d = c.getEl(), b = d.dom.offsetWidth;
					c.setWidth(c.getLayoutTarget().getWidth() + d.getFrameWidth("lr"))
				}
			}
		}
	}
});
Ext.Container.LAYOUTS.menu = Ext.layout.MenuLayout;
Ext.menu.Menu = Ext.extend(Ext.Container, {
	minWidth : 120,
	shadow : "sides",
	subMenuAlign : "tl-tr?",
	defaultAlign : "tl-bl?",
	allowOtherMenus : false,
	ignoreParentClicks : false,
	enableScrolling : true,
	maxHeight : null,
	scrollIncrement : 24,
	showSeparator : true,
	defaultOffsets : [ 0, 0 ],
	floating : true,
	hidden : true,
	layout : "menu",
	hideMode : "offsets",
	scrollerHeight : 8,
	autoLayout : true,
	defaultType : "menuitem",
	initComponent : function() {
		if (Ext.isArray(this.initialConfig)) {
			Ext.apply(this, {
				items : this.initialConfig
			})
		}
		this.addEvents("click", "mouseover", "mouseout", "itemclick");
		Ext.menu.MenuMgr.register(this);
		if (this.floating) {
			Ext.EventManager.onWindowResize(this.hide, this)
		} else {
			if (this.initialConfig.hidden !== false) {
				this.hidden = false
			}
			this.internalDefaults = {
				hideOnClick : false
			}
		}
		Ext.menu.Menu.superclass.initComponent.call(this);
		if (this.autoLayout) {
			this.on({
				add : this.doLayout,
				remove : this.doLayout,
				scope : this
			})
		}
	},
	getLayoutTarget : function() {
		return this.ul
	},
	onRender : function(b, a) {
		if (!b) {
			b = Ext.getBody()
		}
		var c = {
			id : this.getId(),
			cls : "x-menu " + ((this.floating) ? "x-menu-floating x-layer " : "") + (this.cls || "")
					+ (this.plain ? " x-menu-plain" : "") + (this.showSeparator ? "" : " x-menu-nosep"),
			style : this.style,
			cn : [ {
				tag : "a",
				cls : "x-menu-focus",
				href : "#",
				onclick : "return false;",
				tabIndex : "-1"
			}, {
				tag : "ul",
				cls : "x-menu-list"
			} ]
		};
		if (this.floating) {
			this.el = new Ext.Layer({
				shadow : this.shadow,
				dh : c,
				constrain : false,
				parentEl : b,
				zindex : 15000
			})
		} else {
			this.el = b.createChild(c)
		}
		Ext.menu.Menu.superclass.onRender.call(this, b, a);
		if (!this.keyNav) {
			this.keyNav = new Ext.menu.MenuNav(this)
		}
		this.focusEl = this.el.child("a.x-menu-focus");
		this.ul = this.el.child("ul.x-menu-list");
		this.mon(this.ul, {
			scope : this,
			click : this.onClick,
			mouseover : this.onMouseOver,
			mouseout : this.onMouseOut
		});
		if (this.enableScrolling) {
			this.mon(this.el, {
				scope : this,
				delegate : ".x-menu-scroller",
				click : this.onScroll,
				mouseover : this.deactivateActive
			})
		}
	},
	findTargetItem : function(b) {
		var a = b.getTarget(".x-menu-list-item", this.ul, true);
		if (a && a.menuItemId) {
			return this.items.get(a.menuItemId)
		}
	},
	onClick : function(b) {
		var a = this.findTargetItem(b);
		if (a) {
			if (a.isFormField) {
				this.setActiveItem(a)
			} else {
				if (a.menu && this.ignoreParentClicks) {
					a.expandMenu();
					b.preventDefault()
				} else {
					if (a.onClick) {
						a.onClick(b);
						this.fireEvent("click", this, a, b)
					}
				}
			}
		}
	},
	setActiveItem : function(a, b) {
		if (a != this.activeItem) {
			this.deactivateActive();
			if ((this.activeItem = a).isFormField) {
				a.focus()
			} else {
				a.activate(b)
			}
		} else {
			if (b) {
				a.expandMenu()
			}
		}
	},
	deactivateActive : function() {
		var b = this.activeItem;
		if (b) {
			if (b.isFormField) {
				if (b.collapse) {
					b.collapse()
				}
			} else {
				b.deactivate()
			}
			delete this.activeItem
		}
	},
	tryActivate : function(f, e) {
		var b = this.items;
		for ( var c = f, a = b.length; c >= 0 && c < a; c += e) {
			var d = b.get(c);
			if (!d.disabled && (d.canActivate || d.isFormField)) {
				this.setActiveItem(d, false);
				return d
			}
		}
		return false
	},
	onMouseOver : function(b) {
		var a = this.findTargetItem(b);
		if (a) {
			if (a.canActivate && !a.disabled) {
				this.setActiveItem(a, true)
			}
		}
		this.over = true;
		this.fireEvent("mouseover", this, b, a)
	},
	onMouseOut : function(b) {
		var a = this.findTargetItem(b);
		if (a) {
			if (a == this.activeItem && a.shouldDeactivate && a.shouldDeactivate(b)) {
				this.activeItem.deactivate();
				delete this.activeItem
			}
		}
		this.over = false;
		this.fireEvent("mouseout", this, b, a)
	},
	onScroll : function(d, b) {
		if (d) {
			d.stopEvent()
		}
		var a = this.ul.dom, c = Ext.fly(b).is(".x-menu-scroller-top");
		a.scrollTop += this.scrollIncrement * (c ? -1 : 1);
		if (c ? a.scrollTop <= 0 : a.scrollTop + this.activeMax >= a.scrollHeight) {
			this.onScrollerOut(null, b)
		}
	},
	onScrollerIn : function(d, b) {
		var a = this.ul.dom, c = Ext.fly(b).is(".x-menu-scroller-top");
		if (c ? a.scrollTop > 0 : a.scrollTop + this.activeMax < a.scrollHeight) {
			Ext.fly(b).addClass([ "x-menu-item-active", "x-menu-scroller-active" ])
		}
	},
	onScrollerOut : function(b, a) {
		Ext.fly(a).removeClass([ "x-menu-item-active", "x-menu-scroller-active" ])
	},
	show : function(b, c, a) {
		if (this.floating) {
			this.parentMenu = a;
			if (!this.el) {
				this.render();
				this.doLayout(false, true)
			}
			if (this.fireEvent("beforeshow", this) !== false) {
				this.showAt(this.el.getAlignToXY(b, c || this.defaultAlign, this.defaultOffsets), a, false)
			}
		} else {
			Ext.menu.Menu.superclass.show.call(this)
		}
	},
	showAt : function(c, b, a) {
		this.parentMenu = b;
		if (!this.el) {
			this.render()
		}
		this.el.setXY(c);
		if (this.enableScrolling) {
			this.constrainScroll(c[1])
		}
		this.el.show();
		Ext.menu.Menu.superclass.onShow.call(this);
		if (Ext.isIE) {
			this.layout.doAutoSize();
			if (!Ext.isIE8) {
				this.el.repaint()
			}
		}
		this.hidden = false;
		this.focus();
		this.fireEvent("show", this)
	},
	constrainScroll : function(c) {
		var a, b = this.ul.setHeight("auto").getHeight();
		if (this.floating) {
			a = this.maxHeight ? this.maxHeight : Ext.fly(this.el.dom.parentNode).getViewSize().height - c
		} else {
			a = this.getHeight()
		}
		if (b > a && a > 0) {
			this.activeMax = a - this.scrollerHeight * 2 - this.el.getFrameWidth("tb")
					- Ext.num(this.el.shadowOffset, 0);
			this.ul.setHeight(this.activeMax);
			this.createScrollers();
			this.el.select(".x-menu-scroller").setDisplayed("")
		} else {
			this.ul.setHeight(b);
			this.el.select(".x-menu-scroller").setDisplayed("none")
		}
		this.ul.dom.scrollTop = 0
	},
	createScrollers : function() {
		if (!this.scroller) {
			this.scroller = {
				pos : 0,
				top : this.el.insertFirst({
					tag : "div",
					cls : "x-menu-scroller x-menu-scroller-top",
					html : "&#160;"
				}),
				bottom : this.el.createChild({
					tag : "div",
					cls : "x-menu-scroller x-menu-scroller-bottom",
					html : "&#160;"
				})
			};
			this.scroller.top.hover(this.onScrollerIn, this.onScrollerOut, this);
			this.scroller.topRepeater = new Ext.util.ClickRepeater(this.scroller.top, {
				listeners : {
					click : this.onScroll.createDelegate(this, [ null, this.scroller.top ], false)
				}
			});
			this.scroller.bottom.hover(this.onScrollerIn, this.onScrollerOut, this);
			this.scroller.bottomRepeater = new Ext.util.ClickRepeater(this.scroller.bottom, {
				listeners : {
					click : this.onScroll.createDelegate(this, [ null, this.scroller.bottom ], false)
				}
			})
		}
	},
	onLayout : function() {
		if (this.isVisible()) {
			if (this.enableScrolling) {
				this.constrainScroll(this.el.getTop())
			}
			if (this.floating) {
				this.el.sync()
			}
		}
	},
	focus : function() {
		if (!this.hidden) {
			this.doFocus.defer(50, this)
		}
	},
	doFocus : function() {
		if (!this.hidden) {
			this.focusEl.focus()
		}
	},
	hide : function(a) {
		this.deepHide = a;
		Ext.menu.Menu.superclass.hide.call(this);
		delete this.deepHide
	},
	onHide : function() {
		Ext.menu.Menu.superclass.onHide.call(this);
		this.deactivateActive();
		if (this.el && this.floating) {
			this.el.hide()
		}
		if (this.deepHide === true && this.parentMenu) {
			this.parentMenu.hide(true)
		}
	},
	lookupComponent : function(a) {
		if (Ext.isString(a)) {
			a = (a == "separator" || a == "-") ? new Ext.menu.Separator() : new Ext.menu.TextItem(a);
			this.applyDefaults(a)
		} else {
			if (Ext.isObject(a)) {
				a = this.getMenuItem(a)
			} else {
				if (a.tagName || a.el) {
					a = new Ext.BoxComponent({
						el : a
					})
				}
			}
		}
		return a
	},
	applyDefaults : function(b) {
		if (!Ext.isString(b)) {
			b = Ext.menu.Menu.superclass.applyDefaults.call(this, b);
			var a = this.internalDefaults;
			if (a) {
				if (b.events) {
					Ext.applyIf(b.initialConfig, a);
					Ext.apply(b, a)
				} else {
					Ext.applyIf(b, a)
				}
			}
		}
		return b
	},
	getMenuItem : function(a) {
		if (!a.isXType) {
			if (!a.xtype && Ext.isBoolean(a.checked)) {
				return new Ext.menu.CheckItem(a)
			}
			return Ext.create(a, this.defaultType)
		}
		return a
	},
	addSeparator : function() {
		return this.add(new Ext.menu.Separator())
	},
	addElement : function(a) {
		return this.add(new Ext.menu.BaseItem(a))
	},
	addItem : function(a) {
		return this.add(a)
	},
	addMenuItem : function(a) {
		return this.add(this.getMenuItem(a))
	},
	addText : function(a) {
		return this.add(new Ext.menu.TextItem(a))
	},
	onDestroy : function() {
		Ext.menu.Menu.superclass.onDestroy.call(this);
		Ext.menu.MenuMgr.unregister(this);
		Ext.EventManager.removeResizeListener(this.hide, this);
		if (this.keyNav) {
			this.keyNav.disable()
		}
		var a = this.scroller;
		if (a) {
			Ext.destroy(a.topRepeater, a.bottomRepeater, a.top, a.bottom)
		}
	}
});
Ext.reg("menu", Ext.menu.Menu);
Ext.menu.MenuNav = Ext.extend(Ext.KeyNav, function() {
	function a(d, c) {
		if (!c.tryActivate(c.items.indexOf(c.activeItem) - 1, -1)) {
			c.tryActivate(c.items.length - 1, -1)
		}
	}
	function b(d, c) {
		if (!c.tryActivate(c.items.indexOf(c.activeItem) + 1, 1)) {
			c.tryActivate(0, 1)
		}
	}
	return {
		constructor : function(c) {
			Ext.menu.MenuNav.superclass.constructor.call(this, c.el);
			this.scope = this.menu = c
		},
		doRelay : function(f, d) {
			var c = f.getKey();
			if (this.menu.activeItem && this.menu.activeItem.isFormField && c != f.TAB) {
				return false
			}
			if (!this.menu.activeItem && f.isNavKeyPress() && c != f.SPACE && c != f.RETURN) {
				this.menu.tryActivate(0, 1);
				return false
			}
			return d.call(this.scope || this, f, this.menu)
		},
		tab : function(d, c) {
			d.stopEvent();
			if (d.shiftKey) {
				a(d, c)
			} else {
				b(d, c)
			}
		},
		up : a,
		down : b,
		right : function(d, c) {
			if (c.activeItem) {
				c.activeItem.expandMenu(true)
			}
		},
		left : function(d, c) {
			c.hide();
			if (c.parentMenu && c.parentMenu.activeItem) {
				c.parentMenu.activeItem.activate()
			}
		},
		enter : function(d, c) {
			if (c.activeItem) {
				d.stopPropagation();
				c.activeItem.onClick(d);
				c.fireEvent("click", this, c.activeItem);
				return true
			}
		}
	}
}());
Ext.menu.MenuMgr = function() {
	var f, d, c = {}, a = false, k = new Date();
	function m() {
		f = {};
		d = new Ext.util.MixedCollection();
		Ext.getDoc().addKeyListener(27, function() {
			if (d.length > 0) {
				h()
			}
		})
	}
	function h() {
		if (d && d.length > 0) {
			var n = d.clone();
			n.each(function(o) {
				o.hide()
			})
		}
	}
	function e(n) {
		d.remove(n);
		if (d.length < 1) {
			Ext.getDoc().un("mousedown", l);
			a = false
		}
	}
	function j(n) {
		var o = d.last();
		k = new Date();
		d.add(n);
		if (!a) {
			Ext.getDoc().on("mousedown", l);
			a = true
		}
		if (n.parentMenu) {
			n.getEl().setZIndex(parseInt(n.parentMenu.getEl().getStyle("z-index"), 10) + 3);
			n.parentMenu.activeChild = n
		} else {
			if (o && o.isVisible()) {
				n.getEl().setZIndex(parseInt(o.getEl().getStyle("z-index"), 10) + 3)
			}
		}
	}
	function b(n) {
		if (n.activeChild) {
			n.activeChild.hide()
		}
		if (n.autoHideTimer) {
			clearTimeout(n.autoHideTimer);
			delete n.autoHideTimer
		}
	}
	function g(n) {
		var o = n.parentMenu;
		if (!o && !n.allowOtherMenus) {
			h()
		} else {
			if (o && o.activeChild) {
				o.activeChild.hide()
			}
		}
	}
	function l(n) {
		if (k.getElapsed() > 50 && d.length > 0 && !n.getTarget(".x-menu")) {
			h()
		}
	}
	function i(o, r) {
		if (r) {
			var q = c[o.group];
			for ( var p = 0, n = q.length; p < n; p++) {
				if (q[p] != o) {
					q[p].setChecked(false)
				}
			}
		}
	}
	return {
		hideAll : function() {
			h()
		},
		register : function(o) {
			if (!f) {
				m()
			}
			f[o.id] = o;
			o.on("beforehide", b);
			o.on("hide", e);
			o.on("beforeshow", g);
			o.on("show", j);
			var n = o.group;
			if (n && o.events.checkchange) {
				if (!c[n]) {
					c[n] = []
				}
				c[n].push(o);
				o.on("checkchange", onCheck)
			}
		},
		get : function(n) {
			if (typeof n == "string") {
				if (!f) {
					return null
				}
				return f[n]
			} else {
				if (n.events) {
					return n
				} else {
					if (typeof n.length == "number") {
						return new Ext.menu.Menu({
							items : n
						})
					} else {
						return Ext.create(n, "menu")
					}
				}
			}
		},
		unregister : function(o) {
			delete f[o.id];
			o.un("beforehide", b);
			o.un("hide", e);
			o.un("beforeshow", g);
			o.un("show", j);
			var n = o.group;
			if (n && o.events.checkchange) {
				c[n].remove(o);
				o.un("checkchange", onCheck)
			}
		},
		registerCheckable : function(n) {
			var o = n.group;
			if (o) {
				if (!c[o]) {
					c[o] = []
				}
				c[o].push(n);
				n.on("beforecheckchange", i)
			}
		},
		unregisterCheckable : function(n) {
			var o = n.group;
			if (o) {
				c[o].remove(n);
				n.un("beforecheckchange", i)
			}
		},
		getCheckedItem : function(p) {
			var q = c[p];
			if (q) {
				for ( var o = 0, n = q.length; o < n; o++) {
					if (q[o].checked) {
						return q[o]
					}
				}
			}
			return null
		},
		setCheckedItem : function(p, r) {
			var q = c[p];
			if (q) {
				for ( var o = 0, n = q.length; o < n; o++) {
					if (q[o].id == r) {
						q[o].setChecked(true)
					}
				}
			}
			return null
		}
	}
}();
Ext.menu.BaseItem = function(a) {
	Ext.menu.BaseItem.superclass.constructor.call(this, a);
	this.addEvents("click", "activate", "deactivate");
	if (this.handler) {
		this.on("click", this.handler, this.scope)
	}
};
Ext.extend(Ext.menu.BaseItem, Ext.Component, {
	canActivate : false,
	activeClass : "x-menu-item-active",
	hideOnClick : true,
	clickHideDelay : 1,
	ctype : "Ext.menu.BaseItem",
	actionMode : "container",
	onRender : function(b, a) {
		Ext.menu.BaseItem.superclass.onRender.apply(this, arguments);
		if (this.ownerCt && this.ownerCt instanceof Ext.menu.Menu) {
			this.parentMenu = this.ownerCt
		} else {
			this.container.addClass("x-menu-list-item");
			this.mon(this.el, "click", this.onClick, this);
			this.mon(this.el, "mouseenter", this.activate, this);
			this.mon(this.el, "mouseleave", this.deactivate, this)
		}
	},
	setHandler : function(b, a) {
		if (this.handler) {
			this.un("click", this.handler, this.scope)
		}
		this.on("click", this.handler = b, this.scope = a)
	},
	onClick : function(a) {
		if (!this.disabled && this.fireEvent("click", this, a) !== false
				&& (this.parentMenu && this.parentMenu.fireEvent("itemclick", this, a) !== false)) {
			this.handleClick(a)
		} else {
			a.stopEvent()
		}
	},
	activate : function() {
		if (this.disabled) {
			return false
		}
		var a = this.container;
		a.addClass(this.activeClass);
		this.region = a.getRegion().adjust(2, 2, -2, -2);
		this.fireEvent("activate", this);
		return true
	},
	deactivate : function() {
		this.container.removeClass(this.activeClass);
		this.fireEvent("deactivate", this)
	},
	shouldDeactivate : function(a) {
		return !this.region || !this.region.contains(a.getPoint())
	},
	handleClick : function(a) {
		if (this.hideOnClick) {
			this.parentMenu.hide.defer(this.clickHideDelay, this.parentMenu, [ true ])
		}
	},
	expandMenu : Ext.emptyFn,
	hideMenu : Ext.emptyFn
});
Ext.reg("menubaseitem", Ext.menu.BaseItem);
Ext.menu.TextItem = function(a) {
	if (typeof a == "string") {
		a = {
			text : a
		}
	}
	Ext.menu.TextItem.superclass.constructor.call(this, a)
};
Ext.extend(Ext.menu.TextItem, Ext.menu.BaseItem, {
	hideOnClick : false,
	itemCls : "x-menu-text",
	onRender : function() {
		var a = document.createElement("span");
		a.className = this.itemCls;
		a.innerHTML = this.text;
		this.el = a;
		Ext.menu.TextItem.superclass.onRender.apply(this, arguments)
	}
});
Ext.reg("menutextitem", Ext.menu.TextItem);
Ext.menu.Separator = function(a) {
	Ext.menu.Separator.superclass.constructor.call(this, a)
};
Ext.extend(Ext.menu.Separator, Ext.menu.BaseItem, {
	itemCls : "x-menu-sep",
	hideOnClick : false,
	activeClass : "",
	onRender : function(a) {
		var b = document.createElement("span");
		b.className = this.itemCls;
		b.innerHTML = "&#160;";
		this.el = b;
		a.addClass("x-menu-sep-li");
		Ext.menu.Separator.superclass.onRender.apply(this, arguments)
	}
});
Ext.reg("menuseparator", Ext.menu.Separator);
Ext.menu.Item = function(a) {
	Ext.menu.Item.superclass.constructor.call(this, a);
	if (this.menu) {
		this.menu = Ext.menu.MenuMgr.get(this.menu)
	}
};
Ext.extend(Ext.menu.Item, Ext.menu.BaseItem, {
	itemCls : "x-menu-item",
	canActivate : true,
	showDelay : 200,
	hideDelay : 200,
	ctype : "Ext.menu.Item",
	onRender : function(d, b) {
		if (!this.itemTpl) {
			this.itemTpl = Ext.menu.Item.prototype.itemTpl = new Ext.XTemplate(
					'<a id="{id}" class="{cls}" hidefocus="true" unselectable="on" href="{href}"',
					'<tpl if="hrefTarget">', ' target="{hrefTarget}"', "</tpl>", ">",
					'<img src="{icon}" class="x-menu-item-icon {iconCls}"/>',
					'<span class="x-menu-item-text">{text}</span>', "</a>")
		}
		var c = this.getTemplateArgs();
		this.el = b ? this.itemTpl.insertBefore(b, c, true) : this.itemTpl.append(d, c, true);
		this.iconEl = this.el.child("img.x-menu-item-icon");
		this.textEl = this.el.child(".x-menu-item-text");
		Ext.menu.Item.superclass.onRender.call(this, d, b)
	},
	getTemplateArgs : function() {
		return {
			id : this.id,
			cls : this.itemCls + (this.menu ? " x-menu-item-arrow" : "") + (this.cls ? " " + this.cls : ""),
			href : this.href || "#",
			hrefTarget : this.hrefTarget,
			icon : this.icon || Ext.BLANK_IMAGE_URL,
			iconCls : this.iconCls || "",
			text : this.itemText || this.text || "&#160;"
		}
	},
	setText : function(a) {
		this.text = a || "&#160;";
		if (this.rendered) {
			this.textEl.update(this.text);
			this.parentMenu.layout.doAutoSize()
		}
	},
	setIconClass : function(a) {
		var b = this.iconCls;
		this.iconCls = a;
		if (this.rendered) {
			this.iconEl.replaceClass(b, this.iconCls)
		}
	},
	beforeDestroy : function() {
		if (this.menu) {
			this.menu.destroy()
		}
		Ext.menu.Item.superclass.beforeDestroy.call(this)
	},
	handleClick : function(a) {
		if (!this.href) {
			a.stopEvent()
		}
		Ext.menu.Item.superclass.handleClick.apply(this, arguments)
	},
	activate : function(a) {
		if (Ext.menu.Item.superclass.activate.apply(this, arguments)) {
			this.focus();
			if (a) {
				this.expandMenu()
			}
		}
		return true
	},
	shouldDeactivate : function(a) {
		if (Ext.menu.Item.superclass.shouldDeactivate.call(this, a)) {
			if (this.menu && this.menu.isVisible()) {
				return !this.menu.getEl().getRegion().contains(a.getPoint())
			}
			return true
		}
		return false
	},
	deactivate : function() {
		Ext.menu.Item.superclass.deactivate.apply(this, arguments);
		this.hideMenu()
	},
	expandMenu : function(a) {
		if (!this.disabled && this.menu) {
			clearTimeout(this.hideTimer);
			delete this.hideTimer;
			if (!this.menu.isVisible() && !this.showTimer) {
				this.showTimer = this.deferExpand.defer(this.showDelay, this, [ a ])
			} else {
				if (this.menu.isVisible() && a) {
					this.menu.tryActivate(0, 1)
				}
			}
		}
	},
	deferExpand : function(a) {
		delete this.showTimer;
		this.menu.show(this.container, this.parentMenu.subMenuAlign || "tl-tr?", this.parentMenu);
		if (a) {
			this.menu.tryActivate(0, 1)
		}
	},
	hideMenu : function() {
		clearTimeout(this.showTimer);
		delete this.showTimer;
		if (!this.hideTimer && this.menu && this.menu.isVisible()) {
			this.hideTimer = this.deferHide.defer(this.hideDelay, this)
		}
	},
	deferHide : function() {
		delete this.hideTimer;
		if (this.menu.over) {
			this.parentMenu.setActiveItem(this, false)
		} else {
			this.menu.hide()
		}
	}
});
Ext.reg("menuitem", Ext.menu.Item);
Ext.menu.CheckItem = function(a) {
	Ext.menu.CheckItem.superclass.constructor.call(this, a);
	this.addEvents("beforecheckchange", "checkchange");
	if (this.checkHandler) {
		this.on("checkchange", this.checkHandler, this.scope)
	}
	Ext.menu.MenuMgr.registerCheckable(this)
};
Ext.extend(Ext.menu.CheckItem, Ext.menu.Item, {
	itemCls : "x-menu-item x-menu-check-item",
	groupClass : "x-menu-group-item",
	checked : false,
	ctype : "Ext.menu.CheckItem",
	onRender : function(a) {
		Ext.menu.CheckItem.superclass.onRender.apply(this, arguments);
		if (this.group) {
			this.el.addClass(this.groupClass)
		}
		if (this.checked) {
			this.checked = false;
			this.setChecked(true, true)
		}
	},
	destroy : function() {
		Ext.menu.MenuMgr.unregisterCheckable(this);
		Ext.menu.CheckItem.superclass.destroy.apply(this, arguments)
	},
	setChecked : function(b, a) {
		if (this.checked != b && this.fireEvent("beforecheckchange", this, b) !== false) {
			if (this.container) {
				this.container[b ? "addClass" : "removeClass"]("x-menu-item-checked")
			}
			this.checked = b;
			if (a !== true) {
				this.fireEvent("checkchange", this, b)
			}
		}
	},
	handleClick : function(a) {
		if (!this.disabled && !(this.checked && this.group)) {
			this.setChecked(!this.checked)
		}
		Ext.menu.CheckItem.superclass.handleClick.apply(this, arguments)
	}
});
Ext.reg("menucheckitem", Ext.menu.CheckItem);
Ext.menu.DateMenu = Ext.extend(Ext.menu.Menu, {
	enableScrolling : false,
	hideOnClick : true,
	cls : "x-date-menu",
	initComponent : function() {
		this.on("beforeshow", this.onBeforeShow, this);
		if (this.strict = (Ext.isIE7 && Ext.isStrict)) {
			this.on("show", this.onShow, this, {
				single : true,
				delay : 20
			})
		}
		Ext.apply(this, {
			plain : true,
			showSeparator : false,
			items : this.picker = new Ext.DatePicker(Ext.apply({
				internalRender : this.strict || !Ext.isIE,
				ctCls : "x-menu-date-item"
			}, this.initialConfig))
		});
		this.picker.purgeListeners();
		Ext.menu.DateMenu.superclass.initComponent.call(this);
		this.relayEvents(this.picker, [ "select" ]);
		this.on("select", this.menuHide, this);
		if (this.handler) {
			this.on("select", this.handler, this.scope || this)
		}
	},
	menuHide : function() {
		if (this.hideOnClick) {
			this.hide(true)
		}
	},
	onBeforeShow : function() {
		if (this.picker) {
			this.picker.hideMonthPicker(true)
		}
	},
	onShow : function() {
		var a = this.picker.getEl();
		a.setWidth(a.getWidth())
	}
});
Ext.reg("datemenu", Ext.menu.DateMenu);
Ext.menu.ColorMenu = Ext.extend(Ext.menu.Menu, {
	enableScrolling : false,
	hideOnClick : true,
	initComponent : function() {
		Ext.apply(this, {
			plain : true,
			showSeparator : false,
			items : this.palette = new Ext.ColorPalette(this.initialConfig)
		});
		this.palette.purgeListeners();
		Ext.menu.ColorMenu.superclass.initComponent.call(this);
		this.relayEvents(this.palette, [ "select" ]);
		this.on("select", this.menuHide, this);
		if (this.handler) {
			this.on("select", this.handler, this.scope || this)
		}
	},
	menuHide : function() {
		if (this.hideOnClick) {
			this.hide(true)
		}
	}
});
Ext.reg("colormenu", Ext.menu.ColorMenu);

Ext.tree.TreePanel = Ext.extend(Ext.Panel, {
	rootVisible : true,
	animate : Ext.enableFx,
	lines : true,
	enableDD : false,
	hlDrop : Ext.enableFx,
	pathSeparator : "/",
	initComponent : function() {
		Ext.tree.TreePanel.superclass.initComponent.call(this);
		if (!this.eventModel) {
			this.eventModel = new Ext.tree.TreeEventModel(this)
		}
		var a = this.loader;
		if (!a) {
			a = new Ext.tree.TreeLoader({
				dataUrl : this.dataUrl,
				requestMethod : this.requestMethod
			})
		} else {
			if (typeof a == "object" && !a.load) {
				a = new Ext.tree.TreeLoader(a)
			}
		}
		this.loader = a;
		this.nodeHash = {};
		if (this.root) {
			var b = this.root;
			delete this.root;
			this.setRootNode(b)
		}
		this.addEvents("append", "remove", "movenode", "insert", "beforeappend", "beforeremove", "beforemovenode",
				"beforeinsert", "beforeload", "load", "textchange", "beforeexpandnode", "beforecollapsenode",
				"expandnode", "disabledchange", "collapsenode", "beforeclick", "click", "checkchange", "dblclick",
				"contextmenu", "beforechildrenrendered", "startdrag", "enddrag", "dragdrop", "beforenodedrop",
				"nodedrop", "nodedragover");
		if (this.singleExpand) {
			this.on("beforeexpandnode", this.restrictExpand, this)
		}
	},
	proxyNodeEvent : function(c, b, a, g, f, e, d) {
		if (c == "collapse" || c == "expand" || c == "beforecollapse" || c == "beforeexpand" || c == "move"
				|| c == "beforemove") {
			c = c + "node"
		}
		return this.fireEvent(c, b, a, g, f, e, d)
	},
	getRootNode : function() {
		return this.root
	},
	setRootNode : function(b) {
		Ext.destroy(this.root);
		if (!b.render) {
			b = this.loader.createNode(b)
		}
		this.root = b;
		b.ownerTree = this;
		b.isRoot = true;
		this.registerNode(b);
		if (!this.rootVisible) {
			var a = b.attributes.uiProvider;
			b.ui = a ? new a(b) : new Ext.tree.RootTreeNodeUI(b)
		}
		if (this.innerCt) {
			this.innerCt.update("");
			this.afterRender()
		}
		return b
	},
	getNodeById : function(a) {
		return this.nodeHash[a]
	},
	registerNode : function(a) {
		this.nodeHash[a.id] = a
	},
	unregisterNode : function(a) {
		delete this.nodeHash[a.id]
	},
	toString : function() {
		return "[Tree" + (this.id ? " " + this.id : "") + "]"
	},
	restrictExpand : function(a) {
		var b = a.parentNode;
		if (b) {
			if (b.expandedChild && b.expandedChild.parentNode == b) {
				b.expandedChild.collapse()
			}
			b.expandedChild = a
		}
	},
	getChecked : function(b, c) {
		c = c || this.root;
		var d = [];
		var e = function() {
			if (this.attributes.checked) {
				d.push(!b ? this : (b == "id" ? this.id : this.attributes[b]))
			}
		};
		c.cascade(e);
		return d
	},
	getEl : function() {
		return this.el
	},
	getLoader : function() {
		return this.loader
	},
	expandAll : function() {
		this.root.expand(true)
	},
	collapseAll : function() {
		this.root.collapse(true)
	},
	getSelectionModel : function() {
		if (!this.selModel) {
			this.selModel = new Ext.tree.DefaultSelectionModel()
		}
		return this.selModel
	},
	expandPath : function(g, a, h) {
		a = a || "id";
		var d = g.split(this.pathSeparator);
		var c = this.root;
		if (c.attributes[a] != d[1]) {
			if (h) {
				h(false, null)
			}
			return
		}
		var b = 1;
		var e = function() {
			if (++b == d.length) {
				if (h) {
					h(true, c)
				}
				return
			}
			var f = c.findChild(a, d[b]);
			if (!f) {
				if (h) {
					h(false, c)
				}
				return
			}
			c = f;
			f.expand(false, false, e)
		};
		c.expand(false, false, e)
	},
	selectPath : function(e, a, g) {
		a = a || "id";
		var c = e.split(this.pathSeparator);
		var b = c.pop();
		if (c.length > 0) {
			var d = function(h, f) {
				if (h && f) {
					var i = f.findChild(a, b);
					if (i) {
						i.select();
						if (g) {
							g(true, i)
						}
					} else {
						if (g) {
							g(false, i)
						}
					}
				} else {
					if (g) {
						g(false, i)
					}
				}
			};
			this.expandPath(c.join(this.pathSeparator), a, d)
		} else {
			this.root.select();
			if (g) {
				g(true, this.root)
			}
		}
	},
	getTreeEl : function() {
		return this.body
	},
	onRender : function(b, a) {
		Ext.tree.TreePanel.superclass.onRender.call(this, b, a);
		this.el.addClass("x-tree");
		this.innerCt = this.body.createChild({
			tag : "ul",
			cls : "x-tree-root-ct "
					+ (this.useArrows ? "x-tree-arrows" : this.lines ? "x-tree-lines" : "x-tree-no-lines")
		})
	},
	initEvents : function() {
		Ext.tree.TreePanel.superclass.initEvents.call(this);
		if (this.containerScroll) {
			Ext.dd.ScrollManager.register(this.body)
		}
		if ((this.enableDD || this.enableDrop) && !this.dropZone) {
			this.dropZone = new Ext.tree.TreeDropZone(this, this.dropConfig || {
				ddGroup : this.ddGroup || "TreeDD",
				appendOnly : this.ddAppendOnly === true
			})
		}
		if ((this.enableDD || this.enableDrag) && !this.dragZone) {
			this.dragZone = new Ext.tree.TreeDragZone(this, this.dragConfig || {
				ddGroup : this.ddGroup || "TreeDD",
				scroll : this.ddScroll
			})
		}
		this.getSelectionModel().init(this)
	},
	afterRender : function() {
		Ext.tree.TreePanel.superclass.afterRender.call(this);
		this.root.render();
		if (!this.rootVisible) {
			this.root.renderChildren()
		}
	},
	onDestroy : function() {
		if (this.rendered) {
			this.body.removeAllListeners();
			Ext.dd.ScrollManager.unregister(this.body);
			if (this.dropZone) {
				this.dropZone.unreg()
			}
			if (this.dragZone) {
				this.dragZone.unreg()
			}
		}
		this.root.destroy();
		this.nodeHash = null;
		Ext.tree.TreePanel.superclass.onDestroy.call(this)
	}
});
Ext.tree.TreePanel.nodeTypes = {};
Ext.reg("treepanel", Ext.tree.TreePanel);
Ext.tree.TreeEventModel = function(a) {
	this.tree = a;
	this.tree.on("render", this.initEvents, this)
};
Ext.tree.TreeEventModel.prototype = {
	initEvents : function() {
		var a = this.tree.getTreeEl();
		a.on("click", this.delegateClick, this);
		if (this.tree.trackMouseOver !== false) {
			this.tree.innerCt.on("mouseover", this.delegateOver, this);
			this.tree.innerCt.on("mouseout", this.delegateOut, this)
		}
		a.on("dblclick", this.delegateDblClick, this);
		a.on("contextmenu", this.delegateContextMenu, this)
	},
	getNode : function(b) {
		var a;
		if (a = b.getTarget(".x-tree-node-el", 10)) {
			var c = Ext.fly(a, "_treeEvents").getAttribute("tree-node-id", "ext");
			if (c) {
				return this.tree.getNodeById(c)
			}
		}
		return null
	},
	getNodeTarget : function(b) {
		var a = b.getTarget(".x-tree-node-icon", 1);
		if (!a) {
			a = b.getTarget(".x-tree-node-el", 6)
		}
		return a
	},
	delegateOut : function(b, a) {
		if (!this.beforeEvent(b)) {
			return
		}
		if (b.getTarget(".x-tree-ec-icon", 1)) {
			var c = this.getNode(b);
			this.onIconOut(b, c);
			if (c == this.lastEcOver) {
				delete this.lastEcOver
			}
		}
		if ((a = this.getNodeTarget(b)) && !b.within(a, true)) {
			this.onNodeOut(b, this.getNode(b))
		}
	},
	delegateOver : function(b, a) {
		if (!this.beforeEvent(b)) {
			return
		}
		if (Ext.isGecko && !this.trackingDoc) {
			Ext.getBody().on("mouseover", this.trackExit, this);
			this.trackingDoc = true
		}
		if (this.lastEcOver) {
			this.onIconOut(b, this.lastEcOver);
			delete this.lastEcOver
		}
		if (b.getTarget(".x-tree-ec-icon", 1)) {
			this.lastEcOver = this.getNode(b);
			this.onIconOver(b, this.lastEcOver)
		}
		if (a = this.getNodeTarget(b)) {
			this.onNodeOver(b, this.getNode(b))
		}
	},
	trackExit : function(a) {
		if (this.lastOverNode && !a.within(this.lastOverNode.ui.getEl())) {
			this.onNodeOut(a, this.lastOverNode);
			delete this.lastOverNode;
			Ext.getBody().un("mouseover", this.trackExit, this);
			this.trackingDoc = false
		}
	},
	delegateClick : function(b, a) {
		if (!this.beforeEvent(b)) {
			return
		}
		if (b.getTarget("input[type=checkbox]", 1)) {
			this.onCheckboxClick(b, this.getNode(b))
		} else {
			if (b.getTarget(".x-tree-ec-icon", 1)) {
				this.onIconClick(b, this.getNode(b))
			} else {
				if (this.getNodeTarget(b)) {
					this.onNodeClick(b, this.getNode(b))
				}
			}
		}
	},
	delegateDblClick : function(b, a) {
		if (this.beforeEvent(b) && this.getNodeTarget(b)) {
			this.onNodeDblClick(b, this.getNode(b))
		}
	},
	delegateContextMenu : function(b, a) {
		if (this.beforeEvent(b) && this.getNodeTarget(b)) {
			this.onNodeContextMenu(b, this.getNode(b))
		}
	},
	onNodeClick : function(b, a) {
		a.ui.onClick(b)
	},
	onNodeOver : function(b, a) {
		this.lastOverNode = a;
		a.ui.onOver(b)
	},
	onNodeOut : function(b, a) {
		a.ui.onOut(b)
	},
	onIconOver : function(b, a) {
		a.ui.addClass("x-tree-ec-over")
	},
	onIconOut : function(b, a) {
		a.ui.removeClass("x-tree-ec-over")
	},
	onIconClick : function(b, a) {
		a.ui.ecClick(b)
	},
	onCheckboxClick : function(b, a) {
		a.ui.onCheckChange(b)
	},
	onNodeDblClick : function(b, a) {
		a.ui.onDblClick(b)
	},
	onNodeContextMenu : function(b, a) {
		a.ui.onContextMenu(b)
	},
	beforeEvent : function(a) {
		if (this.disabled) {
			a.stopEvent();
			return false
		}
		return true
	},
	disable : function() {
		this.disabled = true
	},
	enable : function() {
		this.disabled = false
	}
};
Ext.tree.DefaultSelectionModel = function(a) {
	this.selNode = null;
	this.addEvents("selectionchange", "beforeselect");
	Ext.apply(this, a);
	Ext.tree.DefaultSelectionModel.superclass.constructor.call(this)
};
Ext.extend(Ext.tree.DefaultSelectionModel, Ext.util.Observable, {
	init : function(a) {
		this.tree = a;
		a.getTreeEl().on("keydown", this.onKeyDown, this);
		a.on("click", this.onNodeClick, this)
	},
	onNodeClick : function(a, b) {
		this.select(a)
	},
	select : function(b) {
		var a = this.selNode;
		if (b == a) {
			b.ui.onSelectedChange(true)
		} else {
			if (this.fireEvent("beforeselect", this, b, a) !== false) {
				if (a) {
					a.ui.onSelectedChange(false)
				}
				this.selNode = b;
				b.ui.onSelectedChange(true);
				this.fireEvent("selectionchange", this, b, a)
			}
		}
		return b
	},
	unselect : function(a) {
		if (this.selNode == a) {
			this.clearSelections()
		}
	},
	clearSelections : function() {
		var a = this.selNode;
		if (a) {
			a.ui.onSelectedChange(false);
			this.selNode = null;
			this.fireEvent("selectionchange", this, null)
		}
		return a
	},
	getSelectedNode : function() {
		return this.selNode
	},
	isSelected : function(a) {
		return this.selNode == a
	},
	selectPrevious : function() {
		var a = this.selNode || this.lastSelNode;
		if (!a) {
			return null
		}
		var c = a.previousSibling;
		if (c) {
			if (!c.isExpanded() || c.childNodes.length < 1) {
				return this.select(c)
			} else {
				var b = c.lastChild;
				while (b && b.isExpanded() && b.childNodes.length > 0) {
					b = b.lastChild
				}
				return this.select(b)
			}
		} else {
			if (a.parentNode && (this.tree.rootVisible || !a.parentNode.isRoot)) {
				return this.select(a.parentNode)
			}
		}
		return null
	},
	selectNext : function() {
		var b = this.selNode || this.lastSelNode;
		if (!b) {
			return null
		}
		if (b.firstChild && b.isExpanded()) {
			return this.select(b.firstChild)
		} else {
			if (b.nextSibling) {
				return this.select(b.nextSibling)
			} else {
				if (b.parentNode) {
					var a = null;
					b.parentNode.bubble(function() {
						if (this.nextSibling) {
							a = this.getOwnerTree().selModel.select(this.nextSibling);
							return false
						}
					});
					return a
				}
			}
		}
		return null
	},
	onKeyDown : function(c) {
		var b = this.selNode || this.lastSelNode;
		var d = this;
		if (!b) {
			return
		}
		var a = c.getKey();
		switch (a) {
		case c.DOWN:
			c.stopEvent();
			this.selectNext();
			break;
		case c.UP:
			c.stopEvent();
			this.selectPrevious();
			break;
		case c.RIGHT:
			c.preventDefault();
			if (b.hasChildNodes()) {
				if (!b.isExpanded()) {
					b.expand()
				} else {
					if (b.firstChild) {
						this.select(b.firstChild, c)
					}
				}
			}
			break;
		case c.LEFT:
			c.preventDefault();
			if (b.hasChildNodes() && b.isExpanded()) {
				b.collapse()
			} else {
				if (b.parentNode && (this.tree.rootVisible || b.parentNode != this.tree.getRootNode())) {
					this.select(b.parentNode, c)
				}
			}
			break
		}
	}
});
Ext.tree.MultiSelectionModel = function(a) {
	this.selNodes = [];
	this.selMap = {};
	this.addEvents("selectionchange");
	Ext.apply(this, a);
	Ext.tree.MultiSelectionModel.superclass.constructor.call(this)
};
Ext.extend(Ext.tree.MultiSelectionModel, Ext.util.Observable, {
	init : function(a) {
		this.tree = a;
		a.getTreeEl().on("keydown", this.onKeyDown, this);
		a.on("click", this.onNodeClick, this)
	},
	onNodeClick : function(a, b) {
		if (b.ctrlKey && this.isSelected(a)) {
			this.unselect(a)
		} else {
			this.select(a, b, b.ctrlKey)
		}
	},
	select : function(a, c, b) {
		if (b !== true) {
			this.clearSelections(true)
		}
		if (this.isSelected(a)) {
			this.lastSelNode = a;
			return a
		}
		this.selNodes.push(a);
		this.selMap[a.id] = a;
		this.lastSelNode = a;
		a.ui.onSelectedChange(true);
		this.fireEvent("selectionchange", this, this.selNodes);
		return a
	},
	unselect : function(b) {
		if (this.selMap[b.id]) {
			b.ui.onSelectedChange(false);
			var c = this.selNodes;
			var a = c.indexOf(b);
			if (a != -1) {
				this.selNodes.splice(a, 1)
			}
			delete this.selMap[b.id];
			this.fireEvent("selectionchange", this, this.selNodes)
		}
	},
	clearSelections : function(b) {
		var d = this.selNodes;
		if (d.length > 0) {
			for ( var c = 0, a = d.length; c < a; c++) {
				d[c].ui.onSelectedChange(false)
			}
			this.selNodes = [];
			this.selMap = {};
			if (b !== true) {
				this.fireEvent("selectionchange", this, this.selNodes)
			}
		}
	},
	isSelected : function(a) {
		return this.selMap[a.id] ? true : false
	},
	getSelectedNodes : function() {
		return this.selNodes
	},
	onKeyDown : Ext.tree.DefaultSelectionModel.prototype.onKeyDown,
	selectNext : Ext.tree.DefaultSelectionModel.prototype.selectNext,
	selectPrevious : Ext.tree.DefaultSelectionModel.prototype.selectPrevious
});
Ext.data.Tree = function(a) {
	this.nodeHash = {};
	this.root = null;
	if (a) {
		this.setRootNode(a)
	}
	this.addEvents("append", "remove", "move", "insert", "beforeappend", "beforeremove", "beforemove", "beforeinsert");
	Ext.data.Tree.superclass.constructor.call(this)
};
Ext.extend(Ext.data.Tree, Ext.util.Observable, {
	pathSeparator : "/",
	proxyNodeEvent : function() {
		return this.fireEvent.apply(this, arguments)
	},
	getRootNode : function() {
		return this.root
	},
	setRootNode : function(a) {
		this.root = a;
		a.ownerTree = this;
		a.isRoot = true;
		this.registerNode(a);
		return a
	},
	getNodeById : function(a) {
		return this.nodeHash[a]
	},
	registerNode : function(a) {
		this.nodeHash[a.id] = a
	},
	unregisterNode : function(a) {
		delete this.nodeHash[a.id]
	},
	toString : function() {
		return "[Tree" + (this.id ? " " + this.id : "") + "]"
	}
});
Ext.data.Node = function(a) {
	this.attributes = a || {};
	this.leaf = this.attributes.leaf;
	this.id = this.attributes.id;
	if (!this.id) {
		this.id = Ext.id(null, "xnode-");
		this.attributes.id = this.id
	}
	this.childNodes = [];
	if (!this.childNodes.indexOf) {
		this.childNodes.indexOf = function(d) {
			for ( var c = 0, b = this.length; c < b; c++) {
				if (this[c] == d) {
					return c
				}
			}
			return -1
		}
	}
	this.parentNode = null;
	this.firstChild = null;
	this.lastChild = null;
	this.previousSibling = null;
	this.nextSibling = null;
	this.addEvents({
		append : true,
		remove : true,
		move : true,
		insert : true,
		beforeappend : true,
		beforeremove : true,
		beforemove : true,
		beforeinsert : true
	});
	this.listeners = this.attributes.listeners;
	Ext.data.Node.superclass.constructor.call(this)
};
Ext.extend(Ext.data.Node, Ext.util.Observable, {
	fireEvent : function(b) {
		if (Ext.data.Node.superclass.fireEvent.apply(this, arguments) === false) {
			return false
		}
		var a = this.getOwnerTree();
		if (a) {
			if (a.proxyNodeEvent.apply(a, arguments) === false) {
				return false
			}
		}
		return true
	},
	isLeaf : function() {
		return this.leaf === true
	},
	setFirstChild : function(a) {
		this.firstChild = a
	},
	setLastChild : function(a) {
		this.lastChild = a
	},
	isLast : function() {
		return (!this.parentNode ? true : this.parentNode.lastChild == this)
	},
	isFirst : function() {
		return (!this.parentNode ? true : this.parentNode.firstChild == this)
	},
	hasChildNodes : function() {
		return !this.isLeaf() && this.childNodes.length > 0
	},
	isExpandable : function() {
		return this.attributes.expandable || this.hasChildNodes()
	},
	appendChild : function(e) {
		var f = false;
		if (Ext.isArray(e)) {
			f = e
		} else {
			if (arguments.length > 1) {
				f = arguments
			}
		}
		if (f) {
			for ( var d = 0, a = f.length; d < a; d++) {
				this.appendChild(f[d])
			}
		} else {
			if (this.fireEvent("beforeappend", this.ownerTree, this, e) === false) {
				return false
			}
			var b = this.childNodes.length;
			var c = e.parentNode;
			if (c) {
				if (e.fireEvent("beforemove", e.getOwnerTree(), e, c, this, b) === false) {
					return false
				}
				c.removeChild(e)
			}
			b = this.childNodes.length;
			if (b === 0) {
				this.setFirstChild(e)
			}
			this.childNodes.push(e);
			e.parentNode = this;
			var g = this.childNodes[b - 1];
			if (g) {
				e.previousSibling = g;
				g.nextSibling = e
			} else {
				e.previousSibling = null
			}
			e.nextSibling = null;
			this.setLastChild(e);
			e.setOwnerTree(this.getOwnerTree());
			this.fireEvent("append", this.ownerTree, this, e, b);
			if (c) {
				e.fireEvent("move", this.ownerTree, e, c, this, b)
			}
			return e
		}
	},
	removeChild : function(b) {
		var a = this.childNodes.indexOf(b);
		if (a == -1) {
			return false
		}
		if (this.fireEvent("beforeremove", this.ownerTree, this, b) === false) {
			return false
		}
		this.childNodes.splice(a, 1);
		if (b.previousSibling) {
			b.previousSibling.nextSibling = b.nextSibling
		}
		if (b.nextSibling) {
			b.nextSibling.previousSibling = b.previousSibling
		}
		if (this.firstChild == b) {
			this.setFirstChild(b.nextSibling)
		}
		if (this.lastChild == b) {
			this.setLastChild(b.previousSibling)
		}
		b.setOwnerTree(null);
		b.parentNode = null;
		b.previousSibling = null;
		b.nextSibling = null;
		this.fireEvent("remove", this.ownerTree, this, b);
		return b
	},
	insertBefore : function(d, a) {
		if (!a) {
			return this.appendChild(d)
		}
		if (d == a) {
			return false
		}
		if (this.fireEvent("beforeinsert", this.ownerTree, this, d, a) === false) {
			return false
		}
		var b = this.childNodes.indexOf(a);
		var c = d.parentNode;
		var e = b;
		if (c == this && this.childNodes.indexOf(d) < b) {
			e--
		}
		if (c) {
			if (d.fireEvent("beforemove", d.getOwnerTree(), d, c, this, b, a) === false) {
				return false
			}
			c.removeChild(d)
		}
		if (e === 0) {
			this.setFirstChild(d)
		}
		this.childNodes.splice(e, 0, d);
		d.parentNode = this;
		var f = this.childNodes[e - 1];
		if (f) {
			d.previousSibling = f;
			f.nextSibling = d
		} else {
			d.previousSibling = null
		}
		d.nextSibling = a;
		a.previousSibling = d;
		d.setOwnerTree(this.getOwnerTree());
		this.fireEvent("insert", this.ownerTree, this, d, a);
		if (c) {
			d.fireEvent("move", this.ownerTree, d, c, this, e, a)
		}
		return d
	},
	remove : function() {
		this.parentNode.removeChild(this);
		return this
	},
	item : function(a) {
		return this.childNodes[a]
	},
	replaceChild : function(a, c) {
		var b = c ? c.nextSibling : null;
		this.removeChild(c);
		this.insertBefore(a, b);
		return c
	},
	indexOf : function(a) {
		return this.childNodes.indexOf(a)
	},
	getOwnerTree : function() {
		if (!this.ownerTree) {
			var a = this;
			while (a) {
				if (a.ownerTree) {
					this.ownerTree = a.ownerTree;
					break
				}
				a = a.parentNode
			}
		}
		return this.ownerTree
	},
	getDepth : function() {
		var b = 0;
		var a = this;
		while (a.parentNode) {
			++b;
			a = a.parentNode
		}
		return b
	},
	setOwnerTree : function(b) {
		if (b != this.ownerTree) {
			if (this.ownerTree) {
				this.ownerTree.unregisterNode(this)
			}
			this.ownerTree = b;
			var d = this.childNodes;
			for ( var c = 0, a = d.length; c < a; c++) {
				d[c].setOwnerTree(b)
			}
			if (b) {
				b.registerNode(this)
			}
		}
	},
	setId : function(b) {
		if (b !== this.id) {
			var a = this.ownerTree;
			if (a) {
				a.unregisterNode(this)
			}
			this.id = b;
			if (a) {
				a.registerNode(this)
			}
			this.onIdChange(b)
		}
	},
	onIdChange : Ext.emptyFn,
	getPath : function(c) {
		c = c || "id";
		var e = this.parentNode;
		var a = [ this.attributes[c] ];
		while (e) {
			a.unshift(e.attributes[c]);
			e = e.parentNode
		}
		var d = this.getOwnerTree().pathSeparator;
		return d + a.join(d)
	},
	bubble : function(c, b, a) {
		var d = this;
		while (d) {
			if (c.apply(b || d, a || [ d ]) === false) {
				break
			}
			d = d.parentNode
		}
	},
	cascade : function(f, e, b) {
		if (f.apply(e || this, b || [ this ]) !== false) {
			var d = this.childNodes;
			for ( var c = 0, a = d.length; c < a; c++) {
				d[c].cascade(f, e, b)
			}
		}
	},
	eachChild : function(f, e, b) {
		var d = this.childNodes;
		for ( var c = 0, a = d.length; c < a; c++) {
			if (f.apply(e || this, b || [ d[c] ]) === false) {
				break
			}
		}
	},
	findChild : function(d, e) {
		var c = this.childNodes;
		for ( var b = 0, a = c.length; b < a; b++) {
			if (c[b].attributes[d] == e) {
				return c[b]
			}
		}
		return null
	},
	findChildBy : function(e, d) {
		var c = this.childNodes;
		for ( var b = 0, a = c.length; b < a; b++) {
			if (e.call(d || c[b], c[b]) === true) {
				return c[b]
			}
		}
		return null
	},
	sort : function(e, d) {
		var c = this.childNodes;
		var a = c.length;
		if (a > 0) {
			var f = d ? function() {
				e.apply(d, arguments)
			} : e;
			c.sort(f);
			for ( var b = 0; b < a; b++) {
				var g = c[b];
				g.previousSibling = c[b - 1];
				g.nextSibling = c[b + 1];
				if (b === 0) {
					this.setFirstChild(g)
				}
				if (b == a - 1) {
					this.setLastChild(g)
				}
			}
		}
	},
	contains : function(a) {
		return a.isAncestor(this)
	},
	isAncestor : function(a) {
		var b = this.parentNode;
		while (b) {
			if (b == a) {
				return true
			}
			b = b.parentNode
		}
		return false
	},
	toString : function() {
		return "[Node" + (this.id ? " " + this.id : "") + "]"
	}
});
Ext.tree.TreeNode = function(a) {
	a = a || {};
	if (typeof a == "string") {
		a = {
			text : a
		}
	}
	this.childrenRendered = false;
	this.rendered = false;
	Ext.tree.TreeNode.superclass.constructor.call(this, a);
	this.expanded = a.expanded === true;
	this.isTarget = a.isTarget !== false;
	this.draggable = a.draggable !== false && a.allowDrag !== false;
	this.allowChildren = a.allowChildren !== false && a.allowDrop !== false;
	this.text = a.text;
	this.disabled = a.disabled === true;
	this.hidden = a.hidden === true;
	this.addEvents("textchange", "beforeexpand", "beforecollapse", "expand", "disabledchange", "collapse",
			"beforeclick", "click", "checkchange", "dblclick", "contextmenu", "beforechildrenrendered");
	var b = this.attributes.uiProvider || this.defaultUI || Ext.tree.TreeNodeUI;
	this.ui = new b(this)
};
Ext.extend(Ext.tree.TreeNode, Ext.data.Node, {
	preventHScroll : true,
	isExpanded : function() {
		return this.expanded
	},
	getUI : function() {
		return this.ui
	},
	getLoader : function() {
		var a;
		return this.loader || ((a = this.getOwnerTree()) && a.loader ? a.loader : new Ext.tree.TreeLoader())
	},
	setFirstChild : function(a) {
		var b = this.firstChild;
		Ext.tree.TreeNode.superclass.setFirstChild.call(this, a);
		if (this.childrenRendered && b && a != b) {
			b.renderIndent(true, true)
		}
		if (this.rendered) {
			this.renderIndent(true, true)
		}
	},
	setLastChild : function(b) {
		var a = this.lastChild;
		Ext.tree.TreeNode.superclass.setLastChild.call(this, b);
		if (this.childrenRendered && a && b != a) {
			a.renderIndent(true, true)
		}
		if (this.rendered) {
			this.renderIndent(true, true)
		}
	},
	appendChild : function(b) {
		if (!b.render && !Ext.isArray(b)) {
			b = this.getLoader().createNode(b)
		}
		var a = Ext.tree.TreeNode.superclass.appendChild.call(this, b);
		if (a && this.childrenRendered) {
			a.render()
		}
		this.ui.updateExpandIcon();
		return a
	},
	removeChild : function(a) {
		this.ownerTree.getSelectionModel().unselect(a);
		Ext.tree.TreeNode.superclass.removeChild.apply(this, arguments);
		if (this.childrenRendered) {
			a.ui.remove()
		}
		if (this.childNodes.length < 1) {
			this.collapse(false, false)
		} else {
			this.ui.updateExpandIcon()
		}
		if (!this.firstChild && !this.isHiddenRoot()) {
			this.childrenRendered = false
		}
		return a
	},
	insertBefore : function(c, a) {
		if (!c.render) {
			c = this.getLoader().createNode(c)
		}
		var b = Ext.tree.TreeNode.superclass.insertBefore.call(this, c, a);
		if (b && a && this.childrenRendered) {
			c.render()
		}
		this.ui.updateExpandIcon();
		return b
	},
	setText : function(b) {
		var a = this.text;
		this.text = b;
		this.attributes.text = b;
		if (this.rendered) {
			this.ui.onTextChange(this, b, a)
		}
		this.fireEvent("textchange", this, b, a)
	},
	select : function() {
		this.getOwnerTree().getSelectionModel().select(this)
	},
	unselect : function() {
		this.getOwnerTree().getSelectionModel().unselect(this)
	},
	isSelected : function() {
		return this.getOwnerTree().getSelectionModel().isSelected(this)
	},
	expand : function(a, c, d, b) {
		if (!this.expanded) {
			if (this.fireEvent("beforeexpand", this, a, c) === false) {
				return
			}
			if (!this.childrenRendered) {
				this.renderChildren()
			}
			this.expanded = true;
			if (!this.isHiddenRoot() && (this.getOwnerTree().animate && c !== false) || c) {
				this.ui.animExpand(function() {
					this.fireEvent("expand", this);
					this.runCallback(d, b || this, [ this ]);
					if (a === true) {
						this.expandChildNodes(true)
					}
				}.createDelegate(this));
				return
			} else {
				this.ui.expand();
				this.fireEvent("expand", this);
				this.runCallback(d, b || this, [ this ])
			}
		} else {
			this.runCallback(d, b || this, [ this ])
		}
		if (a === true) {
			this.expandChildNodes(true)
		}
	},
	runCallback : function(a, c, b) {
		if (Ext.isFunction(a)) {
			a.apply(c, b)
		}
	},
	isHiddenRoot : function() {
		return this.isRoot && !this.getOwnerTree().rootVisible
	},
	collapse : function(b, f, g, e) {
		if (this.expanded && !this.isHiddenRoot()) {
			if (this.fireEvent("beforecollapse", this, b, f) === false) {
				return
			}
			this.expanded = false;
			if ((this.getOwnerTree().animate && f !== false) || f) {
				this.ui.animCollapse(function() {
					this.fireEvent("collapse", this);
					this.runCallback(g, e || this, [ this ]);
					if (b === true) {
						this.collapseChildNodes(true)
					}
				}.createDelegate(this));
				return
			} else {
				this.ui.collapse();
				this.fireEvent("collapse", this);
				this.runCallback(g, e || this, [ this ])
			}
		} else {
			if (!this.expanded) {
				this.runCallback(g, e || this, [ this ])
			}
		}
		if (b === true) {
			var d = this.childNodes;
			for ( var c = 0, a = d.length; c < a; c++) {
				d[c].collapse(true, false)
			}
		}
	},
	delayedExpand : function(a) {
		if (!this.expandProcId) {
			this.expandProcId = this.expand.defer(a, this)
		}
	},
	cancelExpand : function() {
		if (this.expandProcId) {
			clearTimeout(this.expandProcId)
		}
		this.expandProcId = false
	},
	toggle : function() {
		if (this.expanded) {
			this.collapse()
		} else {
			this.expand()
		}
	},
	ensureVisible : function(c, b) {
		var a = this.getOwnerTree();
		a.expandPath(this.parentNode ? this.parentNode.getPath() : this.getPath(), false, function() {
			var d = a.getNodeById(this.id);
			a.getTreeEl().scrollChildIntoView(d.ui.anchor);
			this.runCallback(c, b || this, [ this ])
		}.createDelegate(this))
	},
	expandChildNodes : function(b) {
		var d = this.childNodes;
		for ( var c = 0, a = d.length; c < a; c++) {
			d[c].expand(b)
		}
	},
	collapseChildNodes : function(b) {
		var d = this.childNodes;
		for ( var c = 0, a = d.length; c < a; c++) {
			d[c].collapse(b)
		}
	},
	disable : function() {
		this.disabled = true;
		this.unselect();
		if (this.rendered && this.ui.onDisableChange) {
			this.ui.onDisableChange(this, true)
		}
		this.fireEvent("disabledchange", this, true)
	},
	enable : function() {
		this.disabled = false;
		if (this.rendered && this.ui.onDisableChange) {
			this.ui.onDisableChange(this, false)
		}
		this.fireEvent("disabledchange", this, false)
	},
	renderChildren : function(b) {
		if (b !== false) {
			this.fireEvent("beforechildrenrendered", this)
		}
		var d = this.childNodes;
		for ( var c = 0, a = d.length; c < a; c++) {
			d[c].render(true)
		}
		this.childrenRendered = true
	},
	sort : function(e, d) {
		Ext.tree.TreeNode.superclass.sort.apply(this, arguments);
		if (this.childrenRendered) {
			var c = this.childNodes;
			for ( var b = 0, a = c.length; b < a; b++) {
				c[b].render(true)
			}
		}
	},
	render : function(a) {
		this.ui.render(a);
		if (!this.rendered) {
			this.getOwnerTree().registerNode(this);
			this.rendered = true;
			if (this.expanded) {
				this.expanded = false;
				this.expand(false, false)
			}
		}
	},
	renderIndent : function(b, e) {
		if (e) {
			this.ui.childIndent = null
		}
		this.ui.renderIndent();
		if (b === true && this.childrenRendered) {
			var d = this.childNodes;
			for ( var c = 0, a = d.length; c < a; c++) {
				d[c].renderIndent(true, e)
			}
		}
	},
	beginUpdate : function() {
		this.childrenRendered = false
	},
	endUpdate : function() {
		if (this.expanded && this.rendered) {
			this.renderChildren()
		}
	},
	destroy : function() {
		if (this.childNodes) {
			for ( var b = 0, a = this.childNodes.length; b < a; b++) {
				this.childNodes[b].destroy()
			}
			this.childNodes = null
		}
		if (this.ui.destroy) {
			this.ui.destroy()
		}
	},
	onIdChange : function(a) {
		this.ui.onIdChange(a)
	}
});
Ext.tree.TreePanel.nodeTypes.node = Ext.tree.TreeNode;
Ext.tree.AsyncTreeNode = function(a) {
	this.loaded = a && a.loaded === true;
	this.loading = false;
	Ext.tree.AsyncTreeNode.superclass.constructor.apply(this, arguments);
	this.addEvents("beforeload", "load")
};
Ext.extend(Ext.tree.AsyncTreeNode, Ext.tree.TreeNode, {
	expand : function(b, e, h, c) {
		if (this.loading) {
			var g;
			var d = function() {
				if (!this.loading) {
					clearInterval(g);
					this.expand(b, e, h, c)
				}
			}.createDelegate(this);
			g = setInterval(d, 200);
			return
		}
		if (!this.loaded) {
			if (this.fireEvent("beforeload", this) === false) {
				return
			}
			this.loading = true;
			this.ui.beforeLoad(this);
			var a = this.loader || this.attributes.loader || this.getOwnerTree().getLoader();
			if (a) {
				a.load(this, this.loadComplete.createDelegate(this, [ b, e, h, c ]), this);
				return
			}
		}
		Ext.tree.AsyncTreeNode.superclass.expand.call(this, b, e, h, c)
	},
	isLoading : function() {
		return this.loading
	},
	loadComplete : function(a, c, d, b) {
		this.loading = false;
		this.loaded = true;
		this.ui.afterLoad(this);
		this.fireEvent("load", this);
		this.expand(a, c, d, b)
	},
	isLoaded : function() {
		return this.loaded
	},
	hasChildNodes : function() {
		if (!this.isLeaf() && !this.loaded) {
			return true
		} else {
			return Ext.tree.AsyncTreeNode.superclass.hasChildNodes.call(this)
		}
	},
	reload : function(b, a) {
		this.collapse(false, false);
		while (this.firstChild) {
			this.removeChild(this.firstChild).destroy()
		}
		this.childrenRendered = false;
		this.loaded = false;
		if (this.isHiddenRoot()) {
			this.expanded = false
		}
		this.expand(false, false, b, a)
	}
});
Ext.tree.TreePanel.nodeTypes.async = Ext.tree.AsyncTreeNode;
Ext.tree.TreeNodeUI = function(a) {
	this.node = a;
	this.rendered = false;
	this.animating = false;
	this.wasLeaf = true;
	this.ecc = "x-tree-ec-icon x-tree-elbow";
	this.emptyIcon = Ext.BLANK_IMAGE_URL
};
Ext.tree.TreeNodeUI.prototype = {
	removeChild : function(a) {
		if (this.rendered) {
			this.ctNode.removeChild(a.ui.getEl())
		}
	},
	beforeLoad : function() {
		this.addClass("x-tree-node-loading")
	},
	afterLoad : function() {
		this.removeClass("x-tree-node-loading")
	},
	onTextChange : function(b, c, a) {
		if (this.rendered) {
			this.textNode.innerHTML = c
		}
	},
	onDisableChange : function(a, b) {
		this.disabled = b;
		if (this.checkbox) {
			this.checkbox.disabled = b
		}
		if (b) {
			this.addClass("x-tree-node-disabled")
		} else {
			this.removeClass("x-tree-node-disabled")
		}
	},
	onSelectedChange : function(a) {
		if (a) {
			this.focus();
			this.addClass("x-tree-selected")
		} else {
			this.removeClass("x-tree-selected")
		}
	},
	onMove : function(a, g, e, f, d, b) {
		this.childIndent = null;
		if (this.rendered) {
			var h = f.ui.getContainer();
			if (!h) {
				this.holder = document.createElement("div");
				this.holder.appendChild(this.wrap);
				return
			}
			var c = b ? b.ui.getEl() : null;
			if (c) {
				h.insertBefore(this.wrap, c)
			} else {
				h.appendChild(this.wrap)
			}
			this.node.renderIndent(true, e != f)
		}
	},
	addClass : function(a) {
		if (this.elNode) {
			Ext.fly(this.elNode).addClass(a)
		}
	},
	removeClass : function(a) {
		if (this.elNode) {
			Ext.fly(this.elNode).removeClass(a)
		}
	},
	remove : function() {
		if (this.rendered) {
			this.holder = document.createElement("div");
			this.holder.appendChild(this.wrap)
		}
	},
	fireEvent : function() {
		return this.node.fireEvent.apply(this.node, arguments)
	},
	initEvents : function() {
		this.node.on("move", this.onMove, this);
		if (this.node.disabled) {
			this.addClass("x-tree-node-disabled");
			if (this.checkbox) {
				this.checkbox.disabled = true
			}
		}
		if (this.node.hidden) {
			this.hide()
		}
		var b = this.node.getOwnerTree();
		var a = b.enableDD || b.enableDrag || b.enableDrop;
		if (a && (!this.node.isRoot || b.rootVisible)) {
			Ext.dd.Registry.register(this.elNode, {
				node : this.node,
				handles : this.getDDHandles(),
				isHandle : false
			})
		}
	},
	getDDHandles : function() {
		return [ this.iconNode, this.textNode, this.elNode ]
	},
	hide : function() {
		this.node.hidden = true;
		if (this.wrap) {
			this.wrap.style.display = "none"
		}
	},
	show : function() {
		this.node.hidden = false;
		if (this.wrap) {
			this.wrap.style.display = ""
		}
	},
	onContextMenu : function(a) {
		if (this.node.hasListener("contextmenu") || this.node.getOwnerTree().hasListener("contextmenu")) {
			a.preventDefault();
			this.focus();
			this.fireEvent("contextmenu", this.node, a)
		}
	},
	onClick : function(c) {
		if (this.dropping) {
			c.stopEvent();
			return
		}
		if (this.fireEvent("beforeclick", this.node, c) !== false) {
			var b = c.getTarget("a");
			if (!this.disabled && this.node.attributes.href && b) {
				this.fireEvent("click", this.node, c);
				return
			} else {
				if (b && c.ctrlKey) {
					c.stopEvent()
				}
			}
			c.preventDefault();
			if (this.disabled) {
				return
			}
			if (this.node.attributes.singleClickExpand && !this.animating && this.node.isExpandable()) {
				this.node.toggle()
			}
			this.fireEvent("click", this.node, c)
		} else {
			c.stopEvent()
		}
	},
	onDblClick : function(a) {
		a.preventDefault();
		if (this.disabled) {
			return
		}
		if (this.checkbox) {
			this.toggleCheck()
		}
		if (!this.animating && this.node.isExpandable()) {
			this.node.toggle()
		}
		this.fireEvent("dblclick", this.node, a)
	},
	onOver : function(a) {
		this.addClass("x-tree-node-over")
	},
	onOut : function(a) {
		this.removeClass("x-tree-node-over")
	},
	onCheckChange : function() {
		var a = this.checkbox.checked;
		this.checkbox.defaultChecked = a;
		this.node.attributes.checked = a;
		this.fireEvent("checkchange", this.node, a)
	},
	ecClick : function(a) {
		if (!this.animating && this.node.isExpandable()) {
			this.node.toggle()
		}
	},
	startDrop : function() {
		this.dropping = true
	},
	endDrop : function() {
		setTimeout(function() {
			this.dropping = false
		}.createDelegate(this), 50)
	},
	expand : function() {
		this.updateExpandIcon();
		this.ctNode.style.display = ""
	},
	focus : function() {
		if (!this.node.preventHScroll) {
			try {
				this.anchor.focus()
			} catch (c) {
			}
		} else {
			try {
				var b = this.node.getOwnerTree().getTreeEl().dom;
				var a = b.scrollLeft;
				this.anchor.focus();
				b.scrollLeft = a
			} catch (c) {
			}
		}
	},
	toggleCheck : function(b) {
		var a = this.checkbox;
		if (a) {
			a.checked = (b === undefined ? !a.checked : b);
			this.onCheckChange()
		}
	},
	blur : function() {
		try {
			this.anchor.blur()
		} catch (a) {
		}
	},
	animExpand : function(b) {
		var a = Ext.get(this.ctNode);
		a.stopFx();
		if (!this.node.isExpandable()) {
			this.updateExpandIcon();
			this.ctNode.style.display = "";
			Ext.callback(b);
			return
		}
		this.animating = true;
		this.updateExpandIcon();
		a.slideIn("t", {
			callback : function() {
				this.animating = false;
				Ext.callback(b)
			},
			scope : this,
			duration : this.node.ownerTree.duration || 0.25
		})
	},
	highlight : function() {
		var a = this.node.getOwnerTree();
		Ext.fly(this.wrap).highlight(a.hlColor || "C3DAF9", {
			endColor : a.hlBaseColor
		})
	},
	collapse : function() {
		this.updateExpandIcon();
		this.ctNode.style.display = "none"
	},
	animCollapse : function(b) {
		var a = Ext.get(this.ctNode);
		a.enableDisplayMode("block");
		a.stopFx();
		this.animating = true;
		this.updateExpandIcon();
		a.slideOut("t", {
			callback : function() {
				this.animating = false;
				Ext.callback(b)
			},
			scope : this,
			duration : this.node.ownerTree.duration || 0.25
		})
	},
	getContainer : function() {
		return this.ctNode
	},
	getEl : function() {
		return this.wrap
	},
	appendDDGhost : function(a) {
		a.appendChild(this.elNode.cloneNode(true))
	},
	getDDRepairXY : function() {
		return Ext.lib.Dom.getXY(this.iconNode)
	},
	onRender : function() {
		this.render()
	},
	render : function(c) {
		var e = this.node, b = e.attributes;
		var d = e.parentNode ? e.parentNode.ui.getContainer() : e.ownerTree.innerCt.dom;
		if (!this.rendered) {
			this.rendered = true;
			this.renderElements(e, b, d, c);
			if (b.qtip) {
				if (this.textNode.setAttributeNS) {
					this.textNode.setAttributeNS("ext", "qtip", b.qtip);
					if (b.qtipTitle) {
						this.textNode.setAttributeNS("ext", "qtitle", b.qtipTitle)
					}
				} else {
					this.textNode.setAttribute("ext:qtip", b.qtip);
					if (b.qtipTitle) {
						this.textNode.setAttribute("ext:qtitle", b.qtipTitle)
					}
				}
			} else {
				if (b.qtipCfg) {
					b.qtipCfg.target = Ext.id(this.textNode);
					Ext.QuickTips.register(b.qtipCfg)
				}
			}
			this.initEvents();
			if (!this.node.expanded) {
				this.updateExpandIcon(true)
			}
		} else {
			if (c === true) {
				d.appendChild(this.wrap)
			}
		}
	},
	renderElements : function(e, j, i, k) {
		this.indentMarkup = e.parentNode ? e.parentNode.ui.getChildIndent() : "";
		var f = typeof j.checked == "boolean";
		var c = j.href ? j.href : Ext.isGecko ? "" : "#";
		var d = [
				'<li class="x-tree-node"><div ext:tree-node-id="',
				e.id,
				'" class="x-tree-node-el x-tree-node-leaf x-unselectable ',
				j.cls,
				'" unselectable="on">',
				'<span class="x-tree-node-indent">',
				this.indentMarkup,
				"</span>",
				'<img src="',
				this.emptyIcon,
				'" class="x-tree-ec-icon x-tree-elbow" />',
				'<img src="',
				j.icon || this.emptyIcon,
				'" class="x-tree-node-icon',
				(j.icon ? " x-tree-node-inline-icon" : ""),
				(j.iconCls ? " " + j.iconCls : ""),
				'" unselectable="on" />',
				f ? ('<input class="x-tree-node-cb" type="checkbox" ' + (j.checked ? 'checked="checked" />' : "/>"))
						: "", '<a hidefocus="on" class="x-tree-node-anchor" href="', c, '" tabIndex="1" ',
				j.hrefTarget ? ' target="' + j.hrefTarget + '"' : "", '><span unselectable="on">', e.text,
				"</span></a></div>", '<ul class="x-tree-node-ct" style="display:none;"></ul>', "</li>" ].join("");
		var b;
		if (k !== true && e.nextSibling && (b = e.nextSibling.ui.getEl())) {
			this.wrap = Ext.DomHelper.insertHtml("beforeBegin", b, d)
		} else {
			this.wrap = Ext.DomHelper.insertHtml("beforeEnd", i, d)
		}
		this.elNode = this.wrap.childNodes[0];
		this.ctNode = this.wrap.childNodes[1];
		var h = this.elNode.childNodes;
		this.indentNode = h[0];
		this.ecNode = h[1];
		this.iconNode = h[2];
		var g = 3;
		if (f) {
			this.checkbox = h[3];
			this.checkbox.defaultChecked = this.checkbox.checked;
			g++
		}
		this.anchor = h[g];
		this.textNode = h[g].firstChild
	},
	getAnchor : function() {
		return this.anchor
	},
	getTextEl : function() {
		return this.textNode
	},
	getIconEl : function() {
		return this.iconNode
	},
	isChecked : function() {
		return this.checkbox ? this.checkbox.checked : false
	},
	updateExpandIcon : function() {
		if (this.rendered) {
			var f = this.node, d, c;
			var a = f.isLast() ? "x-tree-elbow-end" : "x-tree-elbow";
			var e = f.hasChildNodes();
			if (e || f.attributes.expandable) {
				if (f.expanded) {
					a += "-minus";
					d = "x-tree-node-collapsed";
					c = "x-tree-node-expanded"
				} else {
					a += "-plus";
					d = "x-tree-node-expanded";
					c = "x-tree-node-collapsed"
				}
				if (this.wasLeaf) {
					this.removeClass("x-tree-node-leaf");
					this.wasLeaf = false
				}
				if (this.c1 != d || this.c2 != c) {
					Ext.fly(this.elNode).replaceClass(d, c);
					this.c1 = d;
					this.c2 = c
				}
			} else {
				if (!this.wasLeaf) {
					Ext.fly(this.elNode).replaceClass("x-tree-node-expanded", "x-tree-node-leaf");
					delete this.c1;
					delete this.c2;
					this.wasLeaf = true
				}
			}
			var b = "x-tree-ec-icon " + a;
			if (this.ecc != b) {
				this.ecNode.className = b;
				this.ecc = b
			}
		}
	},
	onIdChange : function(a) {
		if (this.rendered) {
			this.elNode.setAttribute("ext:tree-node-id", a)
		}
	},
	getChildIndent : function() {
		if (!this.childIndent) {
			var a = [];
			var b = this.node;
			while (b) {
				if (!b.isRoot || (b.isRoot && b.ownerTree.rootVisible)) {
					if (!b.isLast()) {
						a.unshift('<img src="' + this.emptyIcon + '" class="x-tree-elbow-line" />')
					} else {
						a.unshift('<img src="' + this.emptyIcon + '" class="x-tree-icon" />')
					}
				}
				b = b.parentNode
			}
			this.childIndent = a.join("")
		}
		return this.childIndent
	},
	renderIndent : function() {
		if (this.rendered) {
			var a = "";
			var b = this.node.parentNode;
			if (b) {
				a = b.ui.getChildIndent()
			}
			if (this.indentMarkup != a) {
				this.indentNode.innerHTML = a;
				this.indentMarkup = a
			}
			this.updateExpandIcon()
		}
	},
	destroy : function() {
		if (this.elNode) {
			Ext.dd.Registry.unregister(this.elNode.id)
		}
		delete this.elNode;
		delete this.ctNode;
		delete this.indentNode;
		delete this.ecNode;
		delete this.iconNode;
		delete this.checkbox;
		delete this.anchor;
		delete this.textNode;
		if (this.holder) {
			delete this.wrap;
			Ext.removeNode(this.holder);
			delete this.holder
		} else {
			Ext.removeNode(this.wrap);
			delete this.wrap
		}
	}
};
Ext.tree.RootTreeNodeUI = Ext.extend(Ext.tree.TreeNodeUI, {
	render : function() {
		if (!this.rendered) {
			var a = this.node.ownerTree.innerCt.dom;
			this.node.expanded = true;
			a.innerHTML = '<div class="x-tree-root-node"></div>';
			this.wrap = this.ctNode = a.firstChild
		}
	},
	collapse : Ext.emptyFn,
	expand : Ext.emptyFn
});
Ext.tree.TreeLoader = function(a) {
	this.baseParams = {};
	Ext.apply(this, a);
	this.addEvents("beforeload", "load", "loadexception");
	Ext.tree.TreeLoader.superclass.constructor.call(this);
	if (typeof this.paramOrder == "string") {
		this.paramOrder = this.paramOrder.split(/[\s,|]/)
	}
};
Ext.extend(Ext.tree.TreeLoader, Ext.util.Observable, {
	uiProviders : {},
	clearOnLoad : true,
	paramOrder : undefined,
	paramsAsHash : false,
	directFn : undefined,
	load : function(b, c, a) {
		if (this.clearOnLoad) {
			while (b.firstChild) {
				b.removeChild(b.firstChild)
			}
		}
		if (this.doPreload(b)) {
			this.runCallback(c, a || b, [])
		} else {
			if (this.directFn || this.dataUrl || this.url) {
				this.requestData(b, c, a || b)
			}
		}
	},
	doPreload : function(d) {
		if (d.attributes.children) {
			if (d.childNodes.length < 1) {
				var c = d.attributes.children;
				d.beginUpdate();
				for ( var b = 0, a = c.length; b < a; b++) {
					var e = d.appendChild(this.createNode(c[b]));
					if (this.preloadChildren) {
						this.doPreload(e)
					}
				}
				d.endUpdate()
			}
			return true
		}
		return false
	},
	getParams : function(f) {
		var b = [], e = this.baseParams;
		if (this.directFn) {
			b.push(f.id);
			if (e) {
				if (this.paramOrder) {
					for ( var d = 0, a = this.paramOrder.length; d < a; d++) {
						b.push(e[this.paramOrder[d]])
					}
				} else {
					if (this.paramsAsHash) {
						b.push(e)
					}
				}
			}
			return b
		} else {
			for ( var c in e) {
				if (!Ext.isFunction(e[c])) {
					b.push(encodeURIComponent(c), "=", encodeURIComponent(e[c]), "&")
				}
			}
			b.push("node=", encodeURIComponent(f.id));
			return b.join("")
		}
	},
	requestData : function(c, d, b) {
		if (this.fireEvent("beforeload", this, c, d) !== false) {
			if (this.directFn) {
				var a = this.getParams(c);
				a.push(this.processDirectResponse.createDelegate(this, [ {
					callback : d,
					node : c,
					scope : b
				} ], true));
				this.directFn.apply(window, a)
			} else {
				this.transId = Ext.Ajax.request({
					method : this.requestMethod,
					url : this.dataUrl || this.url,
					success : this.handleResponse,
					failure : this.handleFailure,
					scope : this,
					argument : {
						callback : d,
						node : c,
						scope : b
					},
					params : this.getParams(c)
				})
			}
		} else {
			this.runCallback(d, b || c, [])
		}
	},
	processDirectResponse : function(a, b, c) {
		if (b.status) {
			this.handleResponse({
				responseData : Ext.isArray(a) ? a : null,
				responseText : a,
				argument : c
			})
		} else {
			this.handleFailure({
				argument : c
			})
		}
	},
	runCallback : function(a, c, b) {
		if (Ext.isFunction(a)) {
			a.apply(c, b)
		}
	},
	isLoading : function() {
		return !!this.transId
	},
	abort : function() {
		if (this.isLoading()) {
			Ext.Ajax.abort(this.transId)
		}
	},
	createNode : function(attr) {
		if (this.baseAttrs) {
			Ext.applyIf(attr, this.baseAttrs)
		}
		if (this.applyLoader !== false) {
			attr.loader = this
		}
		if (typeof attr.uiProvider == "string") {
			attr.uiProvider = this.uiProviders[attr.uiProvider] || eval(attr.uiProvider)
		}
		if (attr.nodeType) {
			return new Ext.tree.TreePanel.nodeTypes[attr.nodeType](attr)
		} else {
			return attr.leaf ? new Ext.tree.TreeNode(attr) : new Ext.tree.AsyncTreeNode(attr)
		}
	},
	processResponse : function(d, c, j, k) {
		var l = d.responseText;
		try {
			var a = d.responseData || Ext.decode(l);
			c.beginUpdate();
			for ( var f = 0, g = a.length; f < g; f++) {
				var b = this.createNode(a[f]);
				if (b) {
					c.appendChild(b)
				}
			}
			c.endUpdate();
			this.runCallback(j, k || c, [ c ])
		} catch (h) {
			this.handleFailure(d)
		}
	},
	handleResponse : function(c) {
		this.transId = false;
		var b = c.argument;
		this.processResponse(c, b.node, b.callback, b.scope);
		this.fireEvent("load", this, b.node, c)
	},
	handleFailure : function(c) {
		this.transId = false;
		var b = c.argument;
		this.fireEvent("loadexception", this, b.node, c);
		this.runCallback(b.callback, b.scope || b.node, [ b.node ])
	}
});
Ext.tree.TreeFilter = function(a, b) {
	this.tree = a;
	this.filtered = {};
	Ext.apply(this, b)
};
Ext.tree.TreeFilter.prototype = {
	clearBlank : false,
	reverse : false,
	autoClear : false,
	remove : false,
	filter : function(d, a, b) {
		a = a || "text";
		var c;
		if (typeof d == "string") {
			var e = d.length;
			if (e == 0 && this.clearBlank) {
				this.clear();
				return
			}
			d = d.toLowerCase();
			c = function(f) {
				return f.attributes[a].substr(0, e).toLowerCase() == d
			}
		} else {
			if (d.exec) {
				c = function(f) {
					return d.test(f.attributes[a])
				}
			} else {
				throw "Illegal filter type, must be string or regex"
			}
		}
		this.filterBy(c, null, b)
	},
	filterBy : function(d, c, b) {
		b = b || this.tree.root;
		if (this.autoClear) {
			this.clear()
		}
		var a = this.filtered, i = this.reverse;
		var e = function(j) {
			if (j == b) {
				return true
			}
			if (a[j.id]) {
				return false
			}
			var f = d.call(c || j, j);
			if (!f || i) {
				a[j.id] = j;
				j.ui.hide();
				return false
			}
			return true
		};
		b.cascade(e);
		if (this.remove) {
			for ( var h in a) {
				if (typeof h != "function") {
					var g = a[h];
					if (g && g.parentNode) {
						g.parentNode.removeChild(g)
					}
				}
			}
		}
	},
	clear : function() {
		var b = this.tree;
		var a = this.filtered;
		for ( var d in a) {
			if (typeof d != "function") {
				var c = a[d];
				if (c) {
					c.ui.show()
				}
			}
		}
		this.filtered = {}
	}
};
Ext.tree.TreeSorter = function(b, c) {
	Ext.apply(this, c);
	b.on("beforechildrenrendered", this.doSort, this);
	b.on("append", this.updateSort, this);
	b.on("insert", this.updateSort, this);
	b.on("textchange", this.updateSortParent, this);
	var e = this.dir && this.dir.toLowerCase() == "desc";
	var f = this.property || "text";
	var g = this.sortType;
	var a = this.folderSort;
	var d = this.caseSensitive === true;
	var h = this.leafAttr || "leaf";
	this.sortFn = function(j, i) {
		if (a) {
			if (j.attributes[h] && !i.attributes[h]) {
				return 1
			}
			if (!j.attributes[h] && i.attributes[h]) {
				return -1
			}
		}
		var l = g ? g(j) : (d ? j.attributes[f] : j.attributes[f].toUpperCase());
		var k = g ? g(i) : (d ? i.attributes[f] : i.attributes[f].toUpperCase());
		if (l < k) {
			return e ? +1 : -1
		} else {
			if (l > k) {
				return e ? -1 : +1
			} else {
				return 0
			}
		}
	}
};
Ext.tree.TreeSorter.prototype = {
	doSort : function(a) {
		a.sort(this.sortFn)
	},
	compareNodes : function(b, a) {
		return (b.text.toUpperCase() > a.text.toUpperCase() ? 1 : -1)
	},
	updateSort : function(a, b) {
		if (b.childrenRendered) {
			this.doSort.defer(1, this, [ b ])
		}
	},
	updateSortParent : function(a) {
		var b = a.parentNode;
		if (b && b.childrenRendered) {
			this.doSort.defer(1, this, [ b ])
		}
	}
};
if (Ext.dd.DropZone) {
	Ext.tree.TreeDropZone = function(a, b) {
		this.allowParentInsert = b.allowParentInsert || false;
		this.allowContainerDrop = b.allowContainerDrop || false;
		this.appendOnly = b.appendOnly || false;
		Ext.tree.TreeDropZone.superclass.constructor.call(this, a.getTreeEl(), b);
		this.tree = a;
		this.dragOverData = {};
		this.lastInsertClass = "x-tree-no-status"
	};
	Ext.extend(Ext.tree.TreeDropZone, Ext.dd.DropZone, {
		ddGroup : "TreeDD",
		expandDelay : 1000,
		expandNode : function(a) {
			if (a.hasChildNodes() && !a.isExpanded()) {
				a.expand(false, null, this.triggerCacheRefresh.createDelegate(this))
			}
		},
		queueExpand : function(a) {
			this.expandProcId = this.expandNode.defer(this.expandDelay, this, [ a ])
		},
		cancelExpand : function() {
			if (this.expandProcId) {
				clearTimeout(this.expandProcId);
				this.expandProcId = false
			}
		},
		isValidDropPoint : function(a, j, h, d, c) {
			if (!a || !c) {
				return false
			}
			var f = a.node;
			var g = c.node;
			if (!(f && f.isTarget && j)) {
				return false
			}
			if (j == "append" && f.allowChildren === false) {
				return false
			}
			if ((j == "above" || j == "below") && (f.parentNode && f.parentNode.allowChildren === false)) {
				return false
			}
			if (g && (f == g || g.contains(f))) {
				return false
			}
			var b = this.dragOverData;
			b.tree = this.tree;
			b.target = f;
			b.data = c;
			b.point = j;
			b.source = h;
			b.rawEvent = d;
			b.dropNode = g;
			b.cancel = false;
			var i = this.tree.fireEvent("nodedragover", b);
			return b.cancel === false && i !== false
		},
		getDropPoint : function(g, f, k) {
			var l = f.node;
			if (l.isRoot) {
				return l.allowChildren !== false ? "append" : false
			}
			var c = f.ddel;
			var m = Ext.lib.Dom.getY(c), i = m + c.offsetHeight;
			var h = Ext.lib.Event.getPageY(g);
			var j = l.allowChildren === false || l.isLeaf();
			if (this.appendOnly || l.parentNode.allowChildren === false) {
				return j ? false : "append"
			}
			var d = false;
			if (!this.allowParentInsert) {
				d = l.hasChildNodes() && l.isExpanded()
			}
			var a = (i - m) / (j ? 2 : 3);
			if (h >= m && h < (m + a)) {
				return "above"
			} else {
				if (!d && (j || h >= i - a && h <= i)) {
					return "below"
				} else {
					return "append"
				}
			}
		},
		onNodeEnter : function(d, a, c, b) {
			this.cancelExpand()
		},
		onContainerOver : function(a, c, b) {
			if (this.allowContainerDrop && this.isValidDropPoint({
				ddel : this.tree.getRootNode().ui.elNode,
				node : this.tree.getRootNode()
			}, "append", a, c, b)) {
				return this.dropAllowed
			}
			return this.dropNotAllowed
		},
		onNodeOver : function(b, h, g, f) {
			var j = this.getDropPoint(g, b, h);
			var c = b.node;
			if (!this.expandProcId && j == "append" && c.hasChildNodes() && !b.node.isExpanded()) {
				this.queueExpand(c)
			} else {
				if (j != "append") {
					this.cancelExpand()
				}
			}
			var d = this.dropNotAllowed;
			if (this.isValidDropPoint(b, j, h, g, f)) {
				if (j) {
					var a = b.ddel;
					var i;
					if (j == "above") {
						d = b.node.isFirst() ? "x-tree-drop-ok-above" : "x-tree-drop-ok-between";
						i = "x-tree-drag-insert-above"
					} else {
						if (j == "below") {
							d = b.node.isLast() ? "x-tree-drop-ok-below" : "x-tree-drop-ok-between";
							i = "x-tree-drag-insert-below"
						} else {
							d = "x-tree-drop-ok-append";
							i = "x-tree-drag-append"
						}
					}
					if (this.lastInsertClass != i) {
						Ext.fly(a).replaceClass(this.lastInsertClass, i);
						this.lastInsertClass = i
					}
				}
			}
			return d
		},
		onNodeOut : function(d, a, c, b) {
			this.cancelExpand();
			this.removeDropIndicators(d)
		},
		onNodeDrop : function(h, b, g, d) {
			var a = this.getDropPoint(g, h, b);
			var f = h.node;
			f.ui.startDrop();
			if (!this.isValidDropPoint(h, a, b, g, d)) {
				f.ui.endDrop();
				return false
			}
			var c = d.node || (b.getTreeNode ? b.getTreeNode(d, f, a, g) : null);
			return this.processDrop(f, d, a, b, g, c)
		},
		onContainerDrop : function(a, f, c) {
			if (this.allowContainerDrop && this.isValidDropPoint({
				ddel : this.tree.getRootNode().ui.elNode,
				node : this.tree.getRootNode()
			}, "append", a, f, c)) {
				var d = this.tree.getRootNode();
				d.ui.startDrop();
				var b = c.node || (a.getTreeNode ? a.getTreeNode(c, d, "append", f) : null);
				return this.processDrop(d, c, "append", a, f, b)
			}
			return false
		},
		processDrop : function(i, g, b, a, h, d) {
			var f = {
				tree : this.tree,
				target : i,
				data : g,
				point : b,
				source : a,
				rawEvent : h,
				dropNode : d,
				cancel : !d,
				dropStatus : false
			};
			var c = this.tree.fireEvent("beforenodedrop", f);
			if (c === false || f.cancel === true || !f.dropNode) {
				i.ui.endDrop();
				return f.dropStatus
			}
			i = f.target;
			if (b == "append" && !i.isExpanded()) {
				i.expand(false, null, function() {
					this.completeDrop(f)
				}.createDelegate(this))
			} else {
				this.completeDrop(f)
			}
			return true
		},
		completeDrop : function(g) {
			var d = g.dropNode, e = g.point, c = g.target;
			if (!Ext.isArray(d)) {
				d = [ d ]
			}
			var f;
			for ( var b = 0, a = d.length; b < a; b++) {
				f = d[b];
				if (e == "above") {
					c.parentNode.insertBefore(f, c)
				} else {
					if (e == "below") {
						c.parentNode.insertBefore(f, c.nextSibling)
					} else {
						c.appendChild(f)
					}
				}
			}
			f.ui.focus();
			if (Ext.enableFx && this.tree.hlDrop) {
				f.ui.highlight()
			}
			c.ui.endDrop();
			this.tree.fireEvent("nodedrop", g)
		},
		afterNodeMoved : function(a, c, f, d, b) {
			if (Ext.enableFx && this.tree.hlDrop) {
				b.ui.focus();
				b.ui.highlight()
			}
			this.tree.fireEvent("nodedrop", this.tree, d, c, a, f)
		},
		getTree : function() {
			return this.tree
		},
		removeDropIndicators : function(b) {
			if (b && b.ddel) {
				var a = b.ddel;
				Ext.fly(a)
						.removeClass([ "x-tree-drag-insert-above", "x-tree-drag-insert-below", "x-tree-drag-append" ]);
				this.lastInsertClass = "_noclass"
			}
		},
		beforeDragDrop : function(b, a, c) {
			this.cancelExpand();
			return true
		},
		afterRepair : function(a) {
			if (a && Ext.enableFx) {
				a.node.ui.highlight()
			}
			this.hideProxy()
		}
	})
}
if (Ext.dd.DragZone) {
	Ext.tree.TreeDragZone = function(a, b) {
		Ext.tree.TreeDragZone.superclass.constructor.call(this, a.innerCt, b);
		this.tree = a
	};
	Ext.extend(Ext.tree.TreeDragZone, Ext.dd.DragZone, {
		ddGroup : "TreeDD",
		onBeforeDrag : function(a, b) {
			var c = a.node;
			return c && c.draggable && !c.disabled
		},
		onInitDrag : function(b) {
			var a = this.dragData;
			this.tree.getSelectionModel().select(a.node);
			this.tree.eventModel.disable();
			this.proxy.update("");
			a.node.ui.appendDDGhost(this.proxy.ghost.dom);
			this.tree.fireEvent("startdrag", this.tree, a.node, b)
		},
		getRepairXY : function(b, a) {
			return a.node.ui.getDDRepairXY()
		},
		onEndDrag : function(a, b) {
			this.tree.eventModel.enable.defer(100, this.tree.eventModel);
			this.tree.fireEvent("enddrag", this.tree, a.node, b)
		},
		onValidDrop : function(a, b, c) {
			this.tree.fireEvent("dragdrop", this.tree, this.dragData.node, a, b);
			this.hideProxy()
		},
		beforeInvalidDrop : function(a, c) {
			var b = this.tree.getSelectionModel();
			b.clearSelections();
			b.select(this.dragData.node)
		},
		afterRepair : function() {
			if (Ext.enableFx && this.tree.hlDrop) {
				Ext.Element.fly(this.dragData.ddel).highlight(this.hlColor || "c3daf9")
			}
			this.dragging = false
		}
	})
}
Ext.tree.TreeEditor = function(a, c, b) {
	c = c || {};
	var d = c.events ? c : new Ext.form.TextField(c);
	Ext.tree.TreeEditor.superclass.constructor.call(this, d, b);
	this.tree = a;
	if (!a.rendered) {
		a.on("render", this.initEditor, this)
	} else {
		this.initEditor(a)
	}
};
Ext.extend(Ext.tree.TreeEditor, Ext.Editor, {
	alignment : "l-l",
	autoSize : false,
	hideEl : false,
	cls : "x-small-editor x-tree-editor",
	shim : false,
	shadow : "frame",
	maxWidth : 250,
	editDelay : 350,
	initEditor : function(a) {
		a.on("beforeclick", this.beforeNodeClick, this);
		a.on("dblclick", this.onNodeDblClick, this);
		this.on("complete", this.updateNode, this);
		this.on("beforestartedit", this.fitToTree, this);
		this.on("startedit", this.bindScroll, this, {
			delay : 10
		});
		this.on("specialkey", this.onSpecialKey, this)
	},
	fitToTree : function(b, c) {
		var e = this.tree.getTreeEl().dom, d = c.dom;
		if (e.scrollLeft > d.offsetLeft) {
			e.scrollLeft = d.offsetLeft
		}
		var a = Math.min(this.maxWidth, (e.clientWidth > 20 ? e.clientWidth : e.offsetWidth)
				- Math.max(0, d.offsetLeft - e.scrollLeft) - 5);
		this.setSize(a, "")
	},
	triggerEdit : function(a, c) {
		this.completeEdit();
		if (a.attributes.editable !== false) {
			this.editNode = a;
			if (this.tree.autoScroll) {
				Ext.fly(a.ui.getEl()).scrollIntoView(this.tree.body)
			}
			var b = a.text || "";
			if (!Ext.isGecko && Ext.isEmpty(a.text)) {
				a.setText("&#160;")
			}
			this.autoEditTimer = this.startEdit.defer(this.editDelay, this, [ a.ui.textNode, b ]);
			return false
		}
	},
	bindScroll : function() {
		this.tree.getTreeEl().on("scroll", this.cancelEdit, this)
	},
	beforeNodeClick : function(a, b) {
		clearTimeout(this.autoEditTimer);
		if (this.tree.getSelectionModel().isSelected(a)) {
			b.stopEvent();
			return this.triggerEdit(a)
		}
	},
	onNodeDblClick : function(a, b) {
		clearTimeout(this.autoEditTimer)
	},
	updateNode : function(a, b) {
		this.tree.getTreeEl().un("scroll", this.cancelEdit, this);
		this.editNode.setText(b)
	},
	onHide : function() {
		Ext.tree.TreeEditor.superclass.onHide.call(this);
		if (this.editNode) {
			this.editNode.ui.focus.defer(50, this.editNode.ui)
		}
	},
	onSpecialKey : function(c, b) {
		var a = b.getKey();
		if (a == b.ESC) {
			b.stopEvent();
			this.cancelEdit()
		} else {
			if (a == b.ENTER && !b.hasModifier()) {
				b.stopEvent();
				this.completeEdit()
			}
		}
	}
});